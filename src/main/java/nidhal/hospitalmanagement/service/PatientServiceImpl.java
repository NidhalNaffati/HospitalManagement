package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.Patient;
import nidhal.hospitalmanagement.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Patient savePatient(Patient patient) {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public void deletePatient(long id) {

    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return null;
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return null;
    }
}
