package med.voll.api.repository;


import med.voll.api.domain.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByPatientIdAndDateTimeBetween(Long idPatient, LocalDateTime firstTimeSlot, LocalDateTime lastTimeSlot);

    Boolean existsByMedicalDoctorIdAndDateTime(Long medicalDoctorId, LocalDateTime dateTime);
}
