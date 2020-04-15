package com.project.hospital.api.controller;

import com.project.hospital.api.entity.Appointment;
import com.project.hospital.api.entity.Doctor;
import com.project.hospital.api.service.DoctorService;
import com.project.hospital.api.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;


    @Autowired
    public DoctorController(DoctorService theDoctorService) {
        doctorService=theDoctorService;
    }
    // add mapping for "/list"

    @GetMapping("/list")
    public String listDoctors(Model theModel) {

        List<Doctor> theDoctors=doctorService.findAll();



        // add to the spring model
        theModel.addAttribute("doctors", theDoctors);

        return "list-doctors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Doctor doc=new Doctor();
        model.addAttribute("doctor",doc);
        return "doctor-form";

    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doc) {

        doctorService.save(doc);

        return "redirect:/doctors/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("doctorId") int theId,
                                    Model theModel) {


        Doctor theDoctor = doctorService.findById(theId);


        theModel.addAttribute("doctor", theDoctor);

        // send over to our form
        return "doctor-form";
    }

    @GetMapping("/delete")
    public String doingDelete(@RequestParam("doctorId") int theId) {


        doctorService.deleteById(theId);

        // send over to our form
        return "redirect:/doctors/list";
    }

    @GetMapping("/showAppointments")
    public String showAppointments(@RequestParam(value="appointments") List<String> appointments,Model model){



        model.addAttribute("appointments",appointments);

        return "list-appointments";

    }



}
