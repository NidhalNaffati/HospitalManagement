package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.MedicalRecord;
import nidhal.hospitalmanagement.service.MedicalRecordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MedicalRecordController {

    private final MedicalRecordServiceImpl medicalRecordService;

    public MedicalRecordController(MedicalRecordServiceImpl medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medicalRecord/{id}")
    public String getMedicalRecord(@PathVariable long id, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
        model.addAttribute("medicalRecord", medicalRecord);
        return "medicalRecord";
    }


    @GetMapping("/medicalRecord")
    public String medicalRecord() {
        return "medicalRecord";
    }
}
