package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.service.DoctorServiceImpl;
import nidhal.hospitalmanagement.service.MedicalRecordServiceImpl;
import nidhal.hospitalmanagement.service.PatientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import static nidhal.hospitalmanagement.service.DoctorServiceImpl.convertMapToAList;

@Controller("/")
public class HomePageController {

    private final DoctorServiceImpl doctorService;

    private final PatientServiceImpl patientService;

    private final MedicalRecordServiceImpl medicalRecordService;

    public HomePageController(DoctorServiceImpl doctorService, PatientServiceImpl patientService, MedicalRecordServiceImpl medicalRecordService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/")
    public String setDoctorsSpecialityChart(Model model) {

        //set up the chart
        model.addAttribute("chartData", convertMapToAList(getNumberOfDoctorsForEachSpeciality()));

        //set up the number of doctors
        model.addAttribute("numberOfDoctors", numberOfDoctors());

        //set up the number of patients
        model.addAttribute("numberOfPatients", numberOfPatients());

        //set up the number of medical records
        model.addAttribute("numberOfMedicalRecords", numberOfMedicalRecords());

        return "index";
    }

    private long numberOfDoctors() {
        return doctorService.getNumberOfDoctors();
    }

    private long numberOfPatients() {
        return patientService.getNumberOfPatients();
    }

    private long numberOfMedicalRecords() {
        return medicalRecordService.getNumberOfMedicalRecords();
    }

    private Map<DoctorSpeciality, Integer> getNumberOfDoctorsForEachSpeciality() {

        Map<DoctorSpeciality, Integer> numberOfDoctorsForEachSpeciality
                = doctorService.getNumberOfDoctorsForEachSpeciality();

        // Print the map to the console for debugging purposes
        numberOfDoctorsForEachSpeciality.forEach((key, value) ->
                System.out.println("Key: " + key + ", Value: " + value));

        return numberOfDoctorsForEachSpeciality;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
