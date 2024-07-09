package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.users.DataAuthenticationUser;
import med.voll.api.domain.users.User;
import med.voll.api.infra.security.DataJWTToken;
import med.voll.api.infra.security.TokenService;
import med.voll.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.username(), dataAuthenticationUser.password());
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){
        var user = userRepository.findByUsername(dataAuthenticationUser.username());

        if(user == null){
            userRepository.save(new User(dataAuthenticationUser.username(), encoder.encode(dataAuthenticationUser.password())));
            return ResponseEntity.ok().build();
        }
        else{
            throw new IllegalArgumentException("Username already in use");
        }

    }
}
