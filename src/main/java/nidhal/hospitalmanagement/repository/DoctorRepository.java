package nidhal.hospitalmanagement.repository;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.email = ?1")
    Doctor findByEmail(String email);

    @Modifying
    @Query("DELETE FROM Doctor d WHERE d.id = ?1")
    void deleteById(Long id);

    @Query("SELECT d FROM Doctor d WHERE d.speciality = ?1")
    Page<Doctor> findBySpecialization(DoctorSpeciality specialization, Pageable pageable);

    @Query("SELECT DISTINCT d.speciality FROM Doctor d")
    List<DoctorSpeciality> findAllSpecialities();

    @Query("SELECT d FROM Doctor d WHERE d.firstName LIKE %?1% OR d.lastName LIKE %?1%")
    Page<Doctor> findByFirstNameContainingOrLastNameContaining(String firstOrLastName, Pageable pageable);


    @Query("SELECT COUNT(d) FROM Doctor d WHERE d.speciality = ?1")
    int countBySpeciality(DoctorSpeciality specialization);

    // create the method that count all doctors
    @Query("SELECT COUNT(d) FROM Doctor d")
    long countDoctors();
}
