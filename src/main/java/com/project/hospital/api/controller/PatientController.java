package com.project.hospital.api.controller;

import com.project.hospital.api.entity.Patient;
import com.project.hospital.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/list")
    public String showList(Model model){

        List<Patient> patients=patientService.findAll();

        model.addAttribute("patients",patients);

        return "list-patients";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Patient pat=new Patient();
        model.addAttribute("patient",pat);
        return "patient-form";

    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient pat) {

        patientService.save(pat);

        return "redirect:/patients/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId") int theId,
                                    Model theModel) {


        Patient thePatient = patientService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("patient", thePatient);

        // send over to our form
        return "patient-form";
    }

    @GetMapping("/delete")
    public String doingDelete(@RequestParam("patientId") int theId) {



        patientService.deleteById(theId);

        // send over to our form
        return "redirect:/patients/list";
    }

    @GetMapping("/showAppointments")
    public String showAppointments(@RequestParam(value="appointments") List<String> appointments,Model model){



        model.addAttribute("appointments",appointments);

        return "list-appointments";

    }
}
