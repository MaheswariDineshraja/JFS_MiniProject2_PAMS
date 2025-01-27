package org.user.app.service;

import org.user.app.repository.AppointmentRepository;
import org.user.app.model.Appointments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService
{
    private AppointmentRepository appointmentRepository;

    public AppointmentService()
    {
    }

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointments> getAllAppointments()
    {
        List<Appointments> appointmentList = appointmentRepository.findAll();
        return appointmentList;
    }

    public void save(Appointments patient)
    {
    	appointmentRepository.save(patient);
    }

    public Appointments findById(int id)
    {
    	Appointments newAppointment =null;
        Optional<Appointments> patient = appointmentRepository.findById(id);
        if(patient.isPresent())
        {
        	newAppointment = patient.get();
        }
        return newAppointment;
    }


    public void deleteById(int id)
    {
    	appointmentRepository.deleteById(id);
    }

//    public void updatePatient(Patient patient)
//    {
//        patientRepository.
//    }
}


