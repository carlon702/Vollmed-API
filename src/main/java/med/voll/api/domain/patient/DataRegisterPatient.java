package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direction.DataDirection;

public record DataRegisterPatient(@NotBlank
                                   String name,
                                  @NotBlank
                                   @Email
                                   String email,
                                  @NotBlank
                                   String phone,
                                  @NotBlank
                                   String document,
                                  @NotNull @Valid DataDirection direction) {
}
