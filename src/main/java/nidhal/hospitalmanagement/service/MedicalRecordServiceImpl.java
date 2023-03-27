package nidhal.hospitalmanagement.service;

import lombok.extern.slf4j.Slf4j;
import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
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
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        // get the medical record from the database
        MedicalRecord existingMedicalRecord = getMedicalRecordById(medicalRecord.getId());

        // update the medical record
        existingMedicalRecord.setHeight(medicalRecord.getHeight());
        existingMedicalRecord.setWeight(medicalRecord.getWeight());
        existingMedicalRecord.setBloodPressure(medicalRecord.getBloodPressure());
        existingMedicalRecord.setBloodType(medicalRecord.getBloodType());
        existingMedicalRecord.setHeartRate(medicalRecord.getHeartRate());
        existingMedicalRecord.setRespiratoryRate(medicalRecord.getRespiratoryRate());
        existingMedicalRecord.setSugarLevel(medicalRecord.getSugarLevel());
        existingMedicalRecord.setSurgeries(medicalRecord.getSurgeries());

        medicalRecordRepository.save(existingMedicalRecord);
    }

    @Override
    public void deleteMedicalRecordByPatientId(long patientId) {
        medicalRecordRepository.deleteByPatientId(patientId);
    }

    @Override
    public MedicalRecord getMedicalRecordByPatientId(long patientId) {
        return medicalRecordRepository
                .getMedicalRecordByPatientId(patientId)
                .orElseThrow(
                        () -> {
                            log.error("your new method is trash");
                            return new NoSuchElementException("OOPS THERE IS NO MEDICAL RECORD ID LIKE THIS ONE: " + patientId);
                        }
                );
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
    public long getNumberOfMedicalRecords() {
        return medicalRecordRepository.countMedicalRecords();
    }
}
