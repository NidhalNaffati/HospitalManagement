package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.MedicalRecord;

public interface MedicalRecordService {
    void saveMedicalRecord(MedicalRecord medicalRecord);

    void updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecordByPatientId(long patientId);

    MedicalRecord getMedicalRecordById(long id);

    MedicalRecord getMedicalRecordByPatientId(long patientId);

    long getNumberOfMedicalRecords();
}
