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
    void saveDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(long id);

    Doctor getDoctorById(Long id);

    Page<Doctor> getAllDoctors(Pageable pageable);

    Page<Doctor> getDoctorsBySpeciality(DoctorSpeciality specialization, Pageable pageable);

    List<DoctorSpeciality> getAllSpecialities();

    Map<DoctorSpeciality, Integer> getNumberOfDoctorsForEachSpeciality();

    long getNumberOfDoctors();

}
