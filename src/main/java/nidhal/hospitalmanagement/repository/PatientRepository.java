package nidhal.hospitalmanagement.repository;

import nidhal.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
