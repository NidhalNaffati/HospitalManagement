package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PatientService {

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(long id);

    Patient getPatientById(Long id);

    Patient getPatientByEmail(String email);

    Page<Patient> getAllPatients(Pageable pageable);

    long getNumberOfPatients();

    Map<Short, Long> getNumberOfPatientsForEachMonth();
}
