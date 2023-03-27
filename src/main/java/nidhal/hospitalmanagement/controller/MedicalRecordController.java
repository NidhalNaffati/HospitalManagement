package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.service.MedicalRecordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

    private final MedicalRecordServiceImpl medicalRecordService;

    public MedicalRecordController(MedicalRecordServiceImpl medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("update")
    public String saveMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
        return "redirect:/patients";
    }

    @GetMapping("{id}")
    public String renderTheMedicalRecordForm(@PathVariable long id, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordByPatientId(id);
        model.addAttribute("medicalRecord", medicalRecord);
        return "medical-record/document";
    }

    @GetMapping("update/{id}")
    public String renderMedicalRecordForm(Model model, @PathVariable long id) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
        model.addAttribute("medicalRecord", medicalRecord);
        return "medical-record/form";
    }
}
