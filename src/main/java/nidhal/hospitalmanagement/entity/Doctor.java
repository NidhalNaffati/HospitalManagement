package nidhal.hospitalmanagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(appliesTo = "doctor")
@DiscriminatorValue("doctor")
public class Doctor extends Person {
    @Enumerated(EnumType.STRING)
    private DoctorSpecialization specialization;


}