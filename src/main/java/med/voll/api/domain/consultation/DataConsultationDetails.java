package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record DataConsultationDetails(Long id, Long idPaciente, Long idMD, LocalDateTime dateTime) {
    public DataConsultationDetails(Consultation consultation) {
        this(consultation.getId(), consultation.getPatient().getId(), consultation.getMedicalDoctor().getId(), consultation.getDateTime());

    }
}
