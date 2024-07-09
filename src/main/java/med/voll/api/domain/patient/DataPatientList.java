package med.voll.api.domain.patient;

public record DataPatientList(Long id, String name, String email, String document) {

    public DataPatientList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getDocument());
}

}
