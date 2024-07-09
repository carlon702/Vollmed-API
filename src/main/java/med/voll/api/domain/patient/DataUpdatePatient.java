package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direction.DataDirection;

public record DataUpdatePatient(@NotNull
                                Long id,
                                String name,
                                String phone,
                                DataDirection direction) {
}
