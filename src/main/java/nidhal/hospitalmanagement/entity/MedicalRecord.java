package nidhal.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private float heartRate;

    private float respiratoryRate;

    private float sugarLevel;

    private float bloodPressure;

    private float weight;

    private float height;

    private short surgeries;

    public MedicalRecord() {
        this.bloodType = null;
        this.heartRate = 0;
        this.respiratoryRate = 0;
        this.sugarLevel = 0;
        this.bloodPressure = 0;
        this.weight = 0;
        this.height = 0;
        this.surgeries = 0;
    }

}