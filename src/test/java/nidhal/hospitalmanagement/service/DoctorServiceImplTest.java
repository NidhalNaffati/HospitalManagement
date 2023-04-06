package nidhal.hospitalmanagement.service;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.entity.Gender;
import nidhal.hospitalmanagement.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl underTestService;

    private Doctor doctor1;
    private Doctor doctor2;


    @BeforeEach
    void setUp() {

        underTestService = new DoctorServiceImpl(doctorRepository);

        doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setFirstName("Jane");
        doctor1.setLastName("Doe");
        doctor1.setEmail("janedoe@example.com");
        doctor1.setGender(Gender.FEMALE);
        doctor1.setAddress("Gafsa, Tunisia");
        doctor1.setSpeciality(DoctorSpeciality.DERMATOLOGY);

        doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setFirstName("John");
        doctor2.setLastName("Doe");
        doctor2.setEmail("johndoe@example.com");
        doctor2.setGender(Gender.MALE);
        doctor2.setAddress("Quds, Palestine");
        doctor2.setSpeciality(DoctorSpeciality.NEUROLOGY);

    }

    @Test
    public void testSaveDoctor() {
        // given
        Doctor doctor = doctor1;
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        // when
        underTestService.saveDoctor(doctor);

        // Verify that the doctorRepository's save method was called exactly once with the doctor object as an argument
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testUpdateDoctor() {
        // given
        // Create a doctor object to be updated
        Doctor doctorToUpdate = doctor1;
        Long doctorId = doctorToUpdate.getId();

        Doctor doctor = doctor2;

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorToUpdate));


        // set the properties to new values
        doctorToUpdate.setFirstName(doctor.getFirstName());
        doctorToUpdate.setLastName(doctor.getLastName());
        doctorToUpdate.setGender(doctor.getGender());
        doctorToUpdate.setAddress(doctor.getAddress());
        doctorToUpdate.setDayOfBirth(doctor.getDayOfBirth());
        doctorToUpdate.setEmail(doctor.getEmail());
        doctorToUpdate.setSpeciality(doctor.getSpeciality());

        // when
        underTestService.updateDoctor(doctorToUpdate);

        // Retrieve the updated doctor object from the database
        Doctor updatedDoctor = doctorRepository.findById(doctorToUpdate.getId()).orElse(null);


        // assert that the Doctor object has been updated correctly
        assertEquals("John", updatedDoctor.getFirstName());
        assertEquals("Doe", updatedDoctor.getLastName());
        assertEquals(Gender.MALE, updatedDoctor.getGender());
        assertEquals("Quds, Palestine", updatedDoctor.getAddress());
        assertEquals("johndoe@example.com", updatedDoctor.getEmail());
        assertEquals(DoctorSpeciality.NEUROLOGY, updatedDoctor.getSpeciality());
        assertNotNull(updatedDoctor.getUpdatedAt());

        // Verify that the doctorRepository's save method was called exactly once with the updatedDoctor object as an argument
        verify(doctorRepository, times(1)).save(updatedDoctor);
    }

    @Test
    public void testUpdateDoctorWithNonexistentId() {
        // Create a doctor object with a non-existent ID
        Doctor doctor = doctor1;

        // Configure the doctorRepository mock to return an empty optional when findById is called with the non-existent ID
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        underTestService.updateDoctor(doctor);

        // Verify that the doctorRepository's save method was never called
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    public void testDeleteDoctorByID() {

        // given - precondition or setup
        long existingDoctorID = doctor1.getId();

        // when -  action or the behaviour that we are going test
        underTestService.deleteDoctor(existingDoctorID);

        // then - verify the output
        verify(doctorRepository, times(1)).deleteById(existingDoctorID);
    }

    @Test
    public void testGetDoctorById() {
        // given
        long doctorId = 1L;

        // Configure the doctorRepository mock to return the doctor object when findById is called with the doctorId
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor1));

        // when
        Doctor foundDoctor = underTestService.getDoctorById(doctorId);

        // then
        assertEquals(doctor1, foundDoctor);
    }

    @Test
    public void testGetAllDoctors() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Doctor> doctorPage = new PageImpl<>(Arrays.asList(doctor1, doctor2));
        when(doctorRepository.findAll(pageable)).thenReturn(doctorPage);

        // when
        Page<Doctor> foundDoctors = underTestService.getAllDoctors(pageable);

        // then
        assertEquals(doctorPage, foundDoctors);
    }

    @Test
    public void testGetDoctorsBySpeciality() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        DoctorSpeciality speciality = DoctorSpeciality.DERMATOLOGY;
        Page<Doctor> page = new PageImpl<>(Collections.singletonList(doctor1), pageable, 1);
        when(doctorRepository.findBySpecialization(speciality, pageable)).thenReturn(page);

        // when
        Page<Doctor> result = underTestService.getDoctorsBySpeciality(speciality, pageable);

        // then
        assertNotNull(result);
        assertEquals(page, result);
    }

    @Test
    public void testGetAllSpecialities() {
        // given
        List<DoctorSpeciality> specialities = Arrays.asList(DoctorSpeciality.DERMATOLOGY, DoctorSpeciality.NEUROLOGY);
        when(doctorRepository.findAllSpecialities()).thenReturn(specialities);

        // when
        List<DoctorSpeciality> result = underTestService.getAllSpecialities();

        // then
        assertNotNull(result);
        assertEquals(specialities, result);
    }

    @Test
    public void testGetNumberOfDoctorsForEachSpeciality() {
        // given
        Map<DoctorSpeciality, Integer> expectedMap = new HashMap<>();
        expectedMap.put(DoctorSpeciality.DERMATOLOGY, 1);
        expectedMap.put(DoctorSpeciality.NEUROLOGY, 1);

        List<DoctorSpeciality> specialities = Arrays.asList(DoctorSpeciality.DERMATOLOGY, DoctorSpeciality.NEUROLOGY);
        when(doctorRepository.findAllSpecialities()).thenReturn(specialities);

        when(doctorRepository.countBySpeciality(DoctorSpeciality.DERMATOLOGY)).thenReturn(1);
        when(doctorRepository.countBySpeciality(DoctorSpeciality.NEUROLOGY)).thenReturn(1);

        // when
        Map<DoctorSpeciality, Integer> result = underTestService.getNumberOfDoctorsForEachSpeciality();

        // then
        assertNotNull(result);
        assertEquals(expectedMap, result);
    }

    @Test
    public void testGetNumberOfDoctors() {
        // given
        long expectedCount = 2L;
        when(doctorRepository.countDoctors()).thenReturn(expectedCount);

        // when
        long result = underTestService.getNumberOfDoctors();

        // then
        assertEquals(expectedCount, result);
    }


}