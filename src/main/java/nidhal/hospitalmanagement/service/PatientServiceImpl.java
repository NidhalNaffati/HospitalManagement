package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.entity.Patient;
import nidhal.hospitalmanagement.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final MedicalRecordServiceImpl medicalRecordService;

    public PatientServiceImpl(PatientRepository patientRepository, MedicalRecordServiceImpl medicalRecordService) {
        this.patientRepository = patientRepository;
        this.medicalRecordService = medicalRecordService;
    }

    @Override
    public Patient savePatient(Patient patient) {

        var savedPatient = patientRepository.save(patient);

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setPatient(savedPatient);

        medicalRecordService.saveMedicalRecord(medicalRecord);

        return savedPatient;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public void deletePatient(long id) {
        // delete the associated medical record
        medicalRecordService.deleteMedicalRecordByPatientId(id);

        // delete the patient
        patientRepository.deleteById(id);
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
        return patientRepository.findAll(pageable);
    }
}
