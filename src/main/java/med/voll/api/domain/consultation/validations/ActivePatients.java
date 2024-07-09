package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActivePatients implements ConsultationValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(DataBookConsultation dataBookConsultation){



        if(dataBookConsultation.idPatient()==null){
            return;
        }

        var activePatient = patientRepository.findAllByActiveById(dataBookConsultation.idPatient());

        if(!activePatient){
            throw new ValidationException("Can't book consultations for inactive patients.");
        }
    }
}
