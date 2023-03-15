package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.Doctor;
import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.service.DoctorServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    private static final int PAGE_SIZE = 6;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/list")
    public String getDoctors(Model model, @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Doctor> doctorPage = doctorService.getAllDoctors(pageable);

        return passTheDoctorsInfoToTheFrontEnd(model, page, doctorPage);
    }

    @GetMapping("/list/{speciality}")
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

    @GetMapping("/add")
    private String addDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctor/create";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {

        doctorService.saveDoctor(doctor);

        return "redirect:/doctor/list";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.updateDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/edit/{id}")
    public String getDoctorById(@PathVariable long id, Model model) {
        Doctor existingDoctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", existingDoctor);
        return "doctor/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/list";
    }

}
