package nidhal.hospitalmanagement.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "patient")
@DiscriminatorValue("patient")
public class Patient extends Person {

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalRecord medicalRecordNumber;

    private String familyEmail;


}
