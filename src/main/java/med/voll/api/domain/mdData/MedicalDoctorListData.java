package med.voll.api.domain.mdData;

public record MedicalDoctorListData(Long id, String nombre, String especialidad, String documento, String email) {


    public MedicalDoctorListData(MedicalDoctor medicalDoctor){
        this(medicalDoctor.getId(), medicalDoctor.getName(), medicalDoctor.getSpeciality().toString(), medicalDoctor.getDocument(), medicalDoctor.getEmail());
    }
}
