package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpecialization;
import nidhal.hospitalmanagement.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
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
    public Page<Doctor> getAllDoctorsBySpecialization(DoctorSpecialization specialization, Pageable pageable) {
        return doctorRepository.findBySpecialization(specialization, pageable);
    }

    @Override
    public Page<Doctor> getAllDoctorsByFirstNameOrLastName(String firstOrLastName, Pageable pageable) {
        return doctorRepository.findByFirstNameContainingOrLastNameContaining(firstOrLastName, firstOrLastName, pageable);
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}
