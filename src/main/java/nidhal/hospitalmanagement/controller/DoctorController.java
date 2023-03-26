package nidhal.hospitalmanagement.controller;

import jakarta.validation.Valid;
import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.service.DoctorServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    private static final int PAGE_SIZE = 6;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("create")
    private String renderTheDoctorForm(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctor/create";
    }

    @PostMapping("save")
    public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult bindingResult) {
        // Check if there are any errors
        if (bindingResult.hasErrors()) {
            // If there are errors, return the form page
            return "doctor/create";
        }
        // If there are no errors, save the doctor
        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

    @GetMapping("delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    @GetMapping("update/{id}")
    public String renderTheUpdateForm(@PathVariable long id, Model model) {
        // Retrieve the doctor object by ID
        Doctor existingDoctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", existingDoctor);
        return "doctor/update";
    }

    @PostMapping("update")
    public String updateDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result) {
        // Check if there are any errors
        if (result.hasErrors()) {
            // If there are errors, return the form page
            return "doctor/update";
        }
        // If there are no errors, save the doctor
        doctorService.updateDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping()
    public String getDoctors(Model model, @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Doctor> doctorPage = doctorService.getAllDoctors(pageable);

        return passTheDoctorsInfoToTheFrontEnd(model, page, doctorPage);
    }

    @GetMapping("{speciality}")
    public String getDoctorsBySpeciality(
            Model model, @PathVariable DoctorSpeciality speciality,
            @RequestParam(defaultValue = "1") int page) {


        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Doctor> doctorPage = doctorService.getDoctorsBySpeciality(speciality, pageable);

        return passTheDoctorsInfoToTheFrontEnd(model, page, doctorPage);
    }

    private String passTheDoctorsInfoToTheFrontEnd(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            Page<Doctor> doctorPage) {

        List<Doctor> doctorList = doctorPage.getContent();

        int totalPages = doctorPage.getTotalPages();

        int totalDoctors = (int) doctorPage.getTotalElements();

        model.addAttribute("totalDoctors", totalDoctors);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "doctor/table";
    }

}
