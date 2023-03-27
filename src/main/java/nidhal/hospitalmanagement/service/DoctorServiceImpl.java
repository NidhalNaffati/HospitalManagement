package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        // create the local date time
        Date currentDate = new Date();

        doctor.setCreatedAt(currentDate);

        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {

        // Get the doctor object from the database
        Doctor existingDoctor = doctorRepository
                .findById(doctor.getId())
                .orElse(null);

        if (existingDoctor == null) return;

        else {
            // Update the properties of the doctor object with the data from the form submission
            existingDoctor.setFirstName(doctor.getFirstName());
            existingDoctor.setLastName(doctor.getLastName());
            existingDoctor.setGender(doctor.getGender());
            existingDoctor.setAddress(doctor.getAddress());
            existingDoctor.setDayOfBirth(doctor.getDayOfBirth());
            existingDoctor.setEmail(doctor.getEmail());
            existingDoctor.setSpeciality(doctor.getSpeciality());

            // create the local date time
            Date currentDate = new Date();
            // update the updated at property
            existingDoctor.setUpdatedAt(currentDate);
        }

        // Save the updated patient object to the database
        doctorRepository.save(existingDoctor);

    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Page<Doctor> getDoctorsBySpeciality(DoctorSpeciality specialization, Pageable pageable) {
        return doctorRepository.findBySpecialization(specialization, pageable);
    }

    @Override
    public List<DoctorSpeciality> getAllSpecialities() {
        return doctorRepository.findAllSpecialities();
    }

    @Override
    public Map<DoctorSpeciality, Integer> getNumberOfDoctorsForEachSpeciality() {

        var numberOfDoctorsForEachSpeciality = new HashMap<DoctorSpeciality, Integer>();

        try {

            List<DoctorSpeciality> specialities = getAllSpecialities();

            for (DoctorSpeciality speciality : specialities) {
                // get the number of doctors for each speciality
                int numberOfDoctors = doctorRepository.countBySpeciality(speciality);
                // insert the speciality and the number of doctors into the map
                numberOfDoctorsForEachSpeciality.put(speciality, numberOfDoctors);
            }

            return numberOfDoctorsForEachSpeciality;

        } catch (Exception e) {
            e.printStackTrace();

            return numberOfDoctorsForEachSpeciality;
        }
    }

    @Override
    public long getNumberOfDoctors() {
        return doctorRepository.countDoctors();
    }


    // I create a List<List<Object>> because this is the format that the addRows() JavaScript method of the Google Charts library expects.
    public static List<List<Object>> convertMapToAList(Map<DoctorSpeciality, Integer> numberOfDoctorsForEachSpeciality) {
        List<List<Object>> list = new ArrayList<>();
        for (Map.Entry<DoctorSpeciality, Integer> entry : numberOfDoctorsForEachSpeciality.entrySet()) {
            list.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
