package nidhal.hospitalmanagement.controller;

import jakarta.validation.Valid;
import nidhal.hospitalmanagement.entity.Patient;
import nidhal.hospitalmanagement.service.PatientServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PatientController {
    private final PatientServiceImpl patientService;

    private static final int PAGE_SIZE = 12;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getPatients(Model model, @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Patient> patientPage = patientService.getAllPatients(pageable);

        List<Patient> patientList = patientPage.getContent();

        int totalPages = patientPage.getTotalPages();

        int totalPatients = (int) patientPage.getTotalElements();

        model.addAttribute("totalPatients", totalPatients);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("patientList", patientList);

        return "patient/table";
    }

    @GetMapping("/add-patient")
    public String add(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/create";
    }

    @PostMapping(value = "patient/save")
    public String save(@Valid @ModelAttribute("patient") Patient patient, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "patient/create";
        }

        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    @PostMapping(value = "/patient/update")
    public String updatePatient(@ModelAttribute("patient") Patient patient) {

        patientService.updatePatient(patient);

        return "redirect:/patients";
    }


    @GetMapping("/patient/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable long id, Model model) {

        // Retrieve the patient object by ID
        Patient existingPatient = patientService.getPatientById(id);

        model.addAttribute("patient", existingPatient);

        return "patient/update";
    }
}
