package nidhal.hospitalmanagement.service;


import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(long id);

    Doctor getDoctorById(Long id);

    Doctor getDoctorByEmail(String email);

    Page<Doctor> getAllDoctors(Pageable pageable);

    Page<Doctor> getDoctorsBySpeciality(DoctorSpeciality specialization, Pageable pageable);

    Page<Doctor> getAllDoctorsByFirstNameOrLastName(String firstOrLastName, Pageable pageable);

    List<DoctorSpeciality> getAllSpecialities();

    Map<DoctorSpeciality, Integer> getNumberOfDoctorsForEachSpeciality();

    long getNumberOfDoctors();

}
