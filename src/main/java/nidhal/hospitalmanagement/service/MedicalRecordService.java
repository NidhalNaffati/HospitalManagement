package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.MedicalRecord;

public interface MedicalRecordService {
    void saveMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecordById(long id);

    void deleteMedicalRecordByPatientId(long patientId);

    MedicalRecord getMedicalRecordById(long id);

    MedicalRecord getMedicalRecordByPatientEmail(String patientEmail);

    MedicalRecord getMedicalRecordByPatientFirstNameOrLastName(String patientFirstNameOrLastName);

    long getNumberOfMedicalRecords();
}
