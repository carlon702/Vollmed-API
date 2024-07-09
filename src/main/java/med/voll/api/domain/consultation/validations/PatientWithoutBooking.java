package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import med.voll.api.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatientWithoutBooking implements ConsultationValidator{

    @Autowired
    private ConsultationRepository patientRepository;
    public void validate(DataBookConsultation dataBookConsultation){
        var firstTimeSlot = dataBookConsultation.dateTime().withHour(7);
        var lastTimeSlot = dataBookConsultation.dateTime().withHour(18);

        var patientWithBooking = patientRepository.existsByPatientIdAndDateTimeBetween(dataBookConsultation.idPatient(), firstTimeSlot, lastTimeSlot);


        if(patientWithBooking){
            throw new ValidationException("The patient already has a booking for that day.");
        }
    }
}
