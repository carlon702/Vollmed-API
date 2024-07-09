package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.mdData.Speciality;

import java.time.LocalDateTime;

public record DataBookConsultation(Long idMD, @NotNull Long idPatient, @NotNull @Future LocalDateTime dateTime, Speciality speciality) {
}
