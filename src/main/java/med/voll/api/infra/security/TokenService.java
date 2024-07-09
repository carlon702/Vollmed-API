package med.voll.api.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import med.voll.api.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



@Service
public class TokenService {
    @Value("${api.security.secret}")
    public String apiSecret;
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if(token==null){
            throw new RuntimeException("Token not present");
        }
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            System.out.println(token);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("voll med").build();
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if(decodedJWT==null || decodedJWT.getSubject() == null){
            throw new RuntimeException("Verification failed");
        }
        return decodedJWT.getSubject();
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
