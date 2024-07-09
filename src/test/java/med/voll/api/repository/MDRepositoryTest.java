//package med.voll.api.repository;
//
//import med.voll.api.domain.consultation.Consultation;
//import med.voll.api.domain.direction.DataDirection;
//import med.voll.api.domain.mdData.DataMDRegister;
//import med.voll.api.domain.mdData.MedicalDoctor;
//import med.voll.api.domain.mdData.Speciality;
//import med.voll.api.domain.patient.DataRegisterPatient;
//import med.voll.api.domain.patient.Patient;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//
//import java.time.LocalDateTime;
//import java.time.temporal.TemporalAdjusters;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//class MDRepositoryTest {
//
//    @Autowired
//    private MDRepository mdRepository;
//    @Autowired
//    private TestEntityManager en;
//    @Test
//    @DisplayName("Must return null when doctor has another booking in that timeslot")
//    void selectMDWithSpecialityWithDateScenario1() {
//
//        var nextMonday10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
//        var doctor = registerDoctor("jose","jose@mail","1234552",Speciality.CARDIOLOGY);
//        var patient= registerPatient("Juan","juan@mail","29292929");
//        registerConsultation(doctor, patient, nextMonday10Am);
//        var freeDoctor = mdRepository.selectMDWithSpecialityWithDate(Speciality.CARDIOLOGY, nextMonday10Am);
//
//        assertThat(freeDoctor).isNull();
//
//    }
//
//    @Test
//    @DisplayName("Must return a doctor for that timeslot")
//    void selectMDWithSpecialityWithDateScenario2() {
//
//        var nextMonday10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
//        var doctor = registerDoctor("jose","jose@mail","1234552",Speciality.CARDIOLOGY);
//
//        var freeDoctor = mdRepository.selectMDWithSpecialityWithDate(Speciality.CARDIOLOGY, nextMonday10Am);
//
//        assertThat(freeDoctor).isEqualTo(doctor);
//
//    }
//
//    private void registerConsultation(MedicalDoctor doctor, Patient patient, LocalDateTime dateTime){
//        en.persist(new Consultation(null, doctor, patient, dateTime, null));
//    }
//    private MedicalDoctor registerDoctor(String name, String email, String document, Speciality speciality){
//        var doctor = new MedicalDoctor(dataDoctor(name,email, document, speciality));
//        en.persist(doctor);
//        return doctor;
//    }
//
//    private Patient registerPatient(String name, String email, String document){
//        var patient = new Patient(dataPatient(name, email, document));
//        en.persist(patient);
//        return patient;
//    }
//
//    private DataMDRegister dataDoctor(String name, String email, String document, Speciality speciality) {
//        return new DataMDRegister(
//                name,
//                email,
//                "61999999999",
//                document,
//                speciality,
//                dataDirection()
//        );
//    }
//
//    private DataRegisterPatient dataPatient(String name, String email, String document) {
//        return new DataRegisterPatient(
//                name,
//                email,
//                "61999999999",
//                document,
//                dataDirection()
//        );
//    }
//
//    private DataDirection dataDirection() {
//        return new DataDirection(
//                " loca",
//                "azul",
//                "acapulpo",
//                "321",
//                "12"
//        );
//    }
//}