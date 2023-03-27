package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.entity.Patient;
import nidhal.hospitalmanagement.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final MedicalRecordServiceImpl medicalRecordService;

    public PatientServiceImpl(PatientRepository patientRepository, MedicalRecordServiceImpl medicalRecordService) {
        this.patientRepository = patientRepository;
        this.medicalRecordService = medicalRecordService;
    }

    @Override
    public void savePatient(Patient patient) {

        // create the local date time
        Date currentDate = new Date();

        patient.setCreatedAt(currentDate);

        patient.setUpdatedAt(currentDate);

        var savedPatient = patientRepository.save(patient);

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setPatient(savedPatient);

        medicalRecordService.saveMedicalRecord(medicalRecord);

    }

    @Override
    public void updatePatient(Patient patient) {

        // Get the patient object from the database
        Patient existingPatient = patientRepository
                .findById(patient.getId())
                .orElse(null);

        if (existingPatient == null) return;

        else {
            // create the local date time
            Date currentDate = new Date();

            // Update the properties of the patient object with the data from the form submission
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setGender(patient.getGender());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setDayOfBirth(patient.getDayOfBirth());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setFamilyEmail(patient.getFamilyEmail());
            existingPatient.setUpdatedAt(currentDate);
        }


        // Save the updated patient object to the database
        patientRepository.save(existingPatient);
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
        return patientRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public long getNumberOfPatients() {
        return patientRepository.countPatients();
    }

    @Override
    public Map<Short, Long> getNumberOfPatientsForEachMonth() {

        int currentYear = Year.now().getValue();

        var numberOfPatientsForEachMonth = new HashMap<Short, Long>();

        long numberOfPatientsForThisMonth;

        for (int month = 1; month <= 12; month++) {
            // get the number of patient for the current month
            numberOfPatientsForThisMonth = patientRepository.countPatientsByMonthAndYear(month, currentYear);

            // add the number of patients for this month to the map
            numberOfPatientsForEachMonth.put((short) month, numberOfPatientsForThisMonth);
        }

        return numberOfPatientsForEachMonth;
    }
}
