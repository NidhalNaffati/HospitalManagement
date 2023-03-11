package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public void deleteMedicalRecordById(long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public void deleteMedicalRecordByPatientId(long patientId) {
        medicalRecordRepository.deleteByPatientId(patientId);
    }


    @Override
    public MedicalRecord getMedicalRecordById(long id) {
        return medicalRecordRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO MEDICAL RECORD ID LIKE THIS ONE: " + id)
                );
    }


    @Override
    public MedicalRecord getMedicalRecordByPatientEmail(String patientEmail) {
        return null;
    }

    @Override
    public MedicalRecord getMedicalRecordByPatientFirstNameOrLastName(String patientFirstNameOrLastName) {
        return null;
    }
}
