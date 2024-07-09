package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class TimeAvailability implements ConsultationValidator{

    public void validate(DataBookConsultation dataBookConsultation){

        var sunday = DayOfWeek.SUNDAY.equals(dataBookConsultation.dateTime().getDayOfWeek());
        var beforeOpening=dataBookConsultation.dateTime().getHour()<7;
        var afterClosing= dataBookConsultation.dateTime().getHour()>19;
        if(sunday || beforeOpening || afterClosing){
            throw new ValidationException("The opening hours are from 7:00 to 19:00, Monday to Saturday.");
        }
    }
}
