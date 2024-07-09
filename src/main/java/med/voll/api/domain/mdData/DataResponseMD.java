package med.voll.api.domain.mdData;

import med.voll.api.domain.direction.DataDirection;

public record DataResponseMD(Long id, String name, String email, String phone, String speciality, String document, DataDirection direction) {
}
