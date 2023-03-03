package nidhal.hospitalmanagement.repository;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpecialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.email = ?1")
    Doctor findByEmail(String email);

    @Query("SELECT d FROM Doctor d WHERE d.specialization = ?1")
    Page<Doctor> findBySpecialization(DoctorSpecialization specialization, Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.firstName LIKE %?1% OR d.lastName LIKE %?1%")
    Page<Doctor> findByFirstNameContainingOrLastNameContaining(String firstOrLastName, String firstOrLastName1, Pageable pageable);
}
