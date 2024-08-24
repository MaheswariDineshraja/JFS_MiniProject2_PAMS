package org.user.app.controller;

import org.user.app.model.Disease;
import org.user.app.model.Doctor;
import org.user.app.service.DiseaseService;
import org.user.app.service.DoctorService;
import org.user.app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller
@RequestMapping("/diseases")
public class DiseaseController
{
    // load employee data
    private DoctorService doctorService;
    private PatientService patientService;
    private DiseaseService diseaseService;
    private List<Disease> theDiseases;

    public DiseaseController(DoctorService doctorService, PatientService patientService, DiseaseService diseaseService)
    {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.diseaseService = diseaseService;
    }

    @GetMapping("/list")
    public String listDoctors(Model theModel)
    {
        theModel.addAttribute("diseaseList",diseaseService.getAllDiseases());
        return "disease/list-disease";
    }

    @GetMapping("/addDisease")
    public String getDoctorForm(Model model)
    {
        Disease disease = new Disease();
        model.addAttribute("disease",disease);
        return "disease/addDisease";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("disease") Disease theDoctor)
    {
        diseaseService.save(theDoctor);
        return "redirect:/diseases/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("diseaseId") int theID,Model model)
    {
        model.addAttribute("disease",diseaseService.findById(theID));
        return "disease/addDisease";
    }

    @Autowired
    @PersistenceContext
    private EntityManager em;
    @GetMapping("/delete")
    @Transactional
    public String deleteDoctor(@RequestParam("diseaseId") int theID)
    {
        Disease a = em.find(Disease.class, theID);
        for (Doctor b : a.getDoctors()) {
            if (b.getDisease() !=null)
            {
                em.remove(a);
            }
        }
        em.remove(a);
        //diseaseService.deleteById(theID);
        return "redirect:/diseases/list";
    }
}

