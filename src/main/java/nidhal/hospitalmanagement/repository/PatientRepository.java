package nidhal.hospitalmanagement.repository;

import nidhal.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // create a query that return the number of patients
    @Query("SELECT COUNT(p) FROM Patient p")
    long countPatients();

    @Query("SELECT COUNT(p) FROM Patient p WHERE MONTH(p.createdAt) = :month  AND YEAR(p.createdAt) = :year ")
    long countPatientsByMonthAndYear(int month, int year);

}
