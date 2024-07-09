package med.voll.api.domain.consultation.validations;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.DataBookCancellation;
import med.voll.api.domain.consultation.DataBookConsultation;
import med.voll.api.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorTimeInAdvance")
public class CancelTimeInAdvance implements CancellationValidator {


    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public void validate(DataBookCancellation dataBookCancellation) {
        Consultation consultation = consultationRepository.getReferenceById(dataBookCancellation.id());
        LocalDateTime now = LocalDateTime.now();
        long differenceTimes = Duration.between(now, dataBookCancellation.dateTime()).toHours();

        if(differenceTimes<24){
            throw new ValidationException("Consultation can't be cancelled with less that 24 hours in advance");
        }
    }
}
