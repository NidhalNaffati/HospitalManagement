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
@Table(name = "doctor")
@DiscriminatorValue("doctor")
public class Doctor extends Person {
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality speciality;


}