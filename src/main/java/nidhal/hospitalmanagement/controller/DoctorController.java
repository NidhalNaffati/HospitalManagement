package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.service.DoctorServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    private static final int PAGE_SIZE = 6;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public String getDoctors(Model model, @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Doctor> doctorPage = doctorService.getAllDoctors(pageable);

        return passTheDoctorsInfoToTheFrontEnd(model, page, doctorPage);
    }

    @GetMapping("/doctors/{speciality}")
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

        return "doctors-table";
    }

    @GetMapping("/doctor/edit/{id}")
    public String getDoctorById(@PathVariable long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        // TODO: not completed
        return "redirect:/doctors";
    }

    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }


}
