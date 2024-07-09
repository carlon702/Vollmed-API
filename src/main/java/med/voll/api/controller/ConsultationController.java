package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.BookConsultationService;
import med.voll.api.domain.consultation.DataBookCancellation;
import med.voll.api.domain.consultation.DataBookConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultations")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class ConsultationController {


    @Autowired
    private BookConsultationService service;

    @PostMapping
    @Transactional
    public ResponseEntity book(@RequestBody @Valid DataBookConsultation dataBookConsultation){

        var response = service.book(dataBookConsultation);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel")
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataBookCancellation dataBookCancellation){

        service.cancel(dataBookCancellation);
        return ResponseEntity.ok("Consultation cancelled successfully");
    }
}
