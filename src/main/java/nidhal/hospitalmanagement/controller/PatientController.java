package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.Patient;
import nidhal.hospitalmanagement.service.PatientServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PatientController {
    private final PatientServiceImpl patientService;

    private static final int PAGE_SIZE = 6;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getPatients(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Patient> patientPage = patientService.getAllPatients(pageable);

        return passPatientsInfoToTheFrontEnd(model, page, patientPage);

    }

    private String passPatientsInfoToTheFrontEnd(Model model, int page, Page<Patient> patientPage) {
        List<Patient> patientList = patientPage.getContent();

        int totalPages = patientPage.getTotalPages();

        int totalPatients = (int) patientPage.getTotalElements();

        model.addAttribute("totalPatients", totalPatients);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("patientList", patientList);

        return model.toString();
    }


}
