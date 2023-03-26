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
@RequestMapping("/patients")
public class PatientController {
    private final PatientServiceImpl patientService;

    private static final int PAGE_SIZE = 12;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("create")
    public String renderPatientFormPage(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "patient/create";
    }

    @PostMapping("save")
    public String savePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result) {
        // Check if there are any errors
        if (result.hasErrors()) {
            // If there are errors, return the form page
            return "patient/create";
        }
        // If there are no errors, save the patient
        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    @GetMapping("delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("update/{id}")
    public String renderTheUpdateForm(@PathVariable long id, Model model) {
        // Retrieve the patient object by ID
        Patient existingPatient = patientService.getPatientById(id);
        model.addAttribute("patient", existingPatient);
        return "patient/update";
    }

    @PostMapping("update")
    public String updatePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result) {
        // Check if there are any errors
        if (result.hasErrors()) {
            // If there are errors, return the form page
            return "patient/update";
        }
        // If there are no errors, save the patient
        patientService.updatePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping()
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
}
