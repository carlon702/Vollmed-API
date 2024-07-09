package med.voll.api.domain.mdData;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direction.Direction;
@Table(name="medical_doctors")
@Entity(name="MD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicalDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Direction direction;

    public MedicalDoctor(DataMDRegister mdData) {
        this.name = mdData.name();
        this.email = mdData.email();
        this.phone = mdData.phone();
        this.document = mdData.document();
        this.direction = new Direction(mdData.direction());
        this.speciality = mdData.speciality();
        this.active = true;
    }

    public void updateData(DataUpdateMedicalDoctor dataUpdateMedicalDoctor) {
        if(dataUpdateMedicalDoctor.name() != null){
            this.name = dataUpdateMedicalDoctor.name();
        }
        if(dataUpdateMedicalDoctor.document() != null){
            this.document = dataUpdateMedicalDoctor.document();
        }
        if(dataUpdateMedicalDoctor.direction() != null){
            this.direction = direction.updateData(dataUpdateMedicalDoctor.direction());
        }

    }

    public void deactivateDoctor() {
        this.active = false;
    }
}
