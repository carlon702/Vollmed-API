package med.voll.api.domain.consultation;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.mdData.MedicalDoctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Table(name="consultations")
@Entity(name="Consultation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "medicalDoctor_id")
    private MedicalDoctor medicalDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime dateTime;

    @Column(name = "cancellation_motive")
    @Enumerated(EnumType.STRING)
    private CancellationReasons cancelationReasons;

    public Consultation( MedicalDoctor medicalDoctor, Patient patient, LocalDateTime dateTime) {
        this.medicalDoctor=medicalDoctor;
        this.patient=patient;
        this.dateTime=dateTime;
    }

    public void cancel(CancellationReasons cancelationReasons) {
        this.cancelationReasons = cancelationReasons;
    }

}
