package med.voll.api.domain.consultation;


import med.voll.api.domain.consultation.validations.CancelTimeInAdvance;
import med.voll.api.domain.consultation.validations.ConsultationValidator;
import med.voll.api.domain.mdData.MedicalDoctor;
import med.voll.api.infra.errores.IntegrityValidation;
import med.voll.api.repository.ConsultationRepository;
import med.voll.api.repository.MDRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MDRepository mdRepository;
    @Autowired
    List<ConsultationValidator> validatorList;
    @Autowired
    CancelTimeInAdvance cancelTimeInAdvance;

    public DataConsultationDetails book(DataBookConsultation dataBookConsultation){

        if(patientRepository.findById(dataBookConsultation.idPatient()).isEmpty()){
            throw new IntegrityValidation("The patient with that id does not exist.");
        }

        if(dataBookConsultation.idMD()!=null && !mdRepository.existsById(dataBookConsultation.idMD())){
            throw new IntegrityValidation("The medical doctor with that id does not exist.");
        }



        validatorList.forEach(v->v.validate(dataBookConsultation));

        var patient = patientRepository.findById(dataBookConsultation.idPatient()).get();


        var md = selectMd(dataBookConsultation);
        if(md==null){
            throw new IntegrityValidation("There's no doctor available for that timeslot with that specialization.");
        }
        var consultation = new Consultation(md, patient, dataBookConsultation.dateTime());

        consultationRepository.save(consultation);

        return new DataConsultationDetails(consultation);
    }

    public void cancel(DataBookCancellation dataBookCancellation){
        if(!consultationRepository.existsById(dataBookCancellation.id())){
            throw new IntegrityValidation("The consultation id provided doesn't exist!.");
        }

        cancelTimeInAdvance.validate(dataBookCancellation);

        var consultation = consultationRepository.getReferenceById(dataBookCancellation.id());
        consultation.cancel(dataBookCancellation.cancellationReasons());

    }

    private MedicalDoctor selectMd(DataBookConsultation dataBookConsultation) {
        if(dataBookConsultation.idMD()!=null){
            return mdRepository.getReferenceById(dataBookConsultation.idMD());
        }
        if(dataBookConsultation.speciality()==null){
            throw new IntegrityValidation("You must select an speciality for that doctor.");
        }

        return mdRepository.selectMDWithSpecialityWithDate(dataBookConsultation.speciality(),dataBookConsultation.dateTime());
    }
}
