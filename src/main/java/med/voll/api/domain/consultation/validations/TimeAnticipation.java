package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeAnticipation implements ConsultationValidator{

    public void validate(DataBookConsultation dataBookConsultation){
        var now = LocalDateTime.now();
        var consultationTime = dataBookConsultation.dateTime();

        var differenceOf30Min = Duration.between(now,consultationTime).toMinutes()<30;

        if(differenceOf30Min){
            throw new ValidationException("The consultation must be booked with at least 30 minutes of anticipation.");
        }
    }
}
