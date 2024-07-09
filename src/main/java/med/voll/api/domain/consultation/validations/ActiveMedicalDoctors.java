package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import med.voll.api.repository.MDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActiveMedicalDoctors implements ConsultationValidator {

    @Autowired
    private MDRepository mdRepository;


    public void validate(DataBookConsultation dataBookConsultation){



        if(dataBookConsultation.idMD()==null){
            return;
        }

        var activeMD = mdRepository.findActiveById(dataBookConsultation.idMD());

        if(!activeMD){
            throw new ValidationException("Can't book consultations for inactive doctor.");
        }
    }
}
