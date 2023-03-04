package nidhal.hospitalmanagement.controller;

import nidhal.hospitalmanagement.entity.DoctorSpeciality;
import nidhal.hospitalmanagement.service.DoctorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

import static nidhal.hospitalmanagement.service.DoctorServiceImpl.convertMapToAList;

@Controller("/")
public class HomePageController {

    private final DoctorServiceImpl doctorService;

    public HomePageController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String setDoctorsSpecialityChart(Model model) {

        Map<DoctorSpeciality, Integer> numberOfDoctorsForEachSpeciality =
                doctorService.getNumberOfDoctorsForEachSpeciality();

        // Print the map to the console for debugging purposes
        numberOfDoctorsForEachSpeciality.forEach((key, value) ->
                System.out.println("Key: " + key + ", Value: " + value));


        model.addAttribute("chartData", convertMapToAList(numberOfDoctorsForEachSpeciality));


        return "index";
    }

}
