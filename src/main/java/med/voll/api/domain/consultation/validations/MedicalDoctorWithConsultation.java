package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataBookConsultation;
import med.voll.api.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicalDoctorWithConsultation  implements ConsultationValidator{
    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(DataBookConsultation dataBookConsultation){
        if(dataBookConsultation.idMD()==null)
            return;

        var medicalDoctorWithConsultation = consultationRepository.existsByMedicalDoctorIdAndDateTime(dataBookConsultation.idMD(),dataBookConsultation.dateTime());

        if(medicalDoctorWithConsultation){
            throw new ValidationException("The selected doctor already has a consultation in that time slot.");
        }
    }
}
