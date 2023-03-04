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
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        // TODO: not completed
        return doctorRepository.save(doctor);
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
    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
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
    public Page<Doctor> getAllDoctorsByFirstNameOrLastName(String firstOrLastName, Pageable pageable) {
        return doctorRepository.findByFirstNameContainingOrLastNameContaining(firstOrLastName, pageable);
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


    // I create a List<List<Object>> because this is the format that the addRows() JavaScript method of the Google Charts library expects.
    public static List<List<Object>> convertMapToAList(Map<DoctorSpeciality, Integer> numberOfDoctorsForEachSpeciality) {
        List<List<Object>> list = new ArrayList<>();
        for (Map.Entry<DoctorSpeciality, Integer> entry : numberOfDoctorsForEachSpeciality.entrySet()) {
            list.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
