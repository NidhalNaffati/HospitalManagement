package nidhal.hospitalmanagement.repository;

import jakarta.transaction.Transactional;
import nidhal.hospitalmanagement.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MedicalRecord m WHERE m.patient.id = ?1")
    void deleteByPatientId(long patientId);

    @Query("SELECT COUNT(m) FROM MedicalRecord m")
    long countMedicalRecords();

    @Query("SELECT m FROM MedicalRecord m WHERE m.patient.id = ?1")
    Optional<MedicalRecord> getMedicalRecordByPatientId(long patientId);
}
