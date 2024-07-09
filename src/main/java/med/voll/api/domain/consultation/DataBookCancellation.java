package med.voll.api.domain.consultation;

import med.voll.api.domain.mdData.MedicalDoctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

public record DataBookCancellation(Long id, MedicalDoctor md, Patient patient, LocalDateTime dateTime, CancellationReasons cancellationReasons) {
}
