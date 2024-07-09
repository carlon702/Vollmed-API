package med.voll.api.domain.mdData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direction.DataDirection;

public record DataUpdateMedicalDoctor(@NotNull Long id, String name, String document, @Valid DataDirection direction) {

}
