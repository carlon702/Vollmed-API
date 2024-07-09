package med.voll.api.domain.patient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direction.Direction;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Embedded
    private Direction direction;
    private Boolean active;

    public Patient(DataRegisterPatient dataRegisterPatient) {
        this.active = true;
        this.name = dataRegisterPatient.name();
        this.email = dataRegisterPatient.email();
        this.phone = dataRegisterPatient.phone();
        this.document = dataRegisterPatient.document();
        this.direction = new Direction(dataRegisterPatient.direction());
    }

    public void updateInformation(DataUpdatePatient dataUpdatePatient) {
        if (dataUpdatePatient.name() != null) {
            this.name = dataUpdatePatient.name();
        }
        if (dataUpdatePatient.phone() != null) {
            this.phone = dataUpdatePatient.phone();
        }
        if (dataUpdatePatient.direction() != null) {
            this.direction.updateData(dataUpdatePatient.direction());
        }
    }

    public void delete() {
        this.active = false;
    }
}
