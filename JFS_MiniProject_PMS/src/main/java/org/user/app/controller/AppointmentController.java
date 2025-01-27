package org.user.app.controller;

import java.util.List;
import org.user.app.model.Appointments;
import org.user.app.service.PatientService;
import org.user.app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;


@Controller
@RequestMapping("/appointments")
public class AppointmentController
{

    // load employee data
    private AppointmentService appointmentService;
    private PatientService patientService;//    
    private List<Appointments> theAppointments;
    @Autowired
    private EntityManager em;

    public AppointmentController(AppointmentService appointmentService,PatientService patientService)
    {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        
    }

    @GetMapping("/list")
    public String listAppointments(Model theModel)
    {
    	theAppointments = appointmentService.getAllAppointments();
        theModel.addAttribute("appointmentsdetails", theAppointments);
        return "appointments/list-appointment";
    }

    @GetMapping("/addAppointment")
    public String getAppointmentForm(Model model)
    {
    	Appointments appointment = new Appointments();
//        model.addAttribute("appointmentList",patientService.getAllPatients());
        model.addAttribute("appointmentsdetails",appointment);
        return "appointments/addAppointment";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointmentsdetails") Appointments theAppointment)
    {

    	appointmentService.save(theAppointment);
        return "redirect:/appointments/list";
    }

    @GetMapping("/showFormForUpdate1")
    public String showUpdateForm(@RequestParam("appointmentId") int theID,Model model)
    {
    	Appointments appointment = appointmentService.findById(theID);
//        model.addAttribute("patientList",patientService.getAllPatients());
        model.addAttribute("appointmentsdetails",appointment);
        return "appointments/addAppointment";
    }

    @GetMapping("/delete")
    public String deleteappointment(@RequestParam("appointmentId") int theID)
    {
    	appointmentService.deleteById(theID);
        return "redirect:/appointments/list";
    }

}
