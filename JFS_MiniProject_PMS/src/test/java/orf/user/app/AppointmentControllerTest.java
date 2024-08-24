package orf.user.app;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.user.app.controller.AppointmentController;
import org.user.app.model.Appointments;
import org.user.app.service.AppointmentService;
import org.user.app.service.PatientService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private PatientService patientService;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAppointments() {
        // Arrange
        List<Appointments> appointmentsList = new ArrayList<>();
        when(appointmentService.getAllAppointments()).thenReturn(appointmentsList);

        // Act
        String viewName = appointmentController.listAppointments(model);

        // Assert
        assertEquals("appointments/list-appointment", viewName);
        verify(model).addAttribute("appointmentsdetails", appointmentsList);
    }

    @Test
    public void testGetAppointmentForm() {
        // Arrange
        Appointments appointment = new Appointments();
        
        // Act
        String viewName = appointmentController.getAppointmentForm(model);

        // Assert
        assertEquals("appointments/addAppointment", viewName);
        verify(model).addAttribute("appointmentsdetails", appointment);
    }

    @Test
    public void testSaveAppointment() {
        // Arrange
        Appointments appointment = new Appointments();

        // Act
        String viewName = appointmentController.saveAppointment(appointment);

        // Assert
        assertEquals("redirect:/appointments/list", viewName);
        verify(appointmentService).save(appointment);
    }

    @Test
    public void testShowUpdateForm() {
        // Arrange
        int appointmentId = 1;
        Appointments appointment = new Appointments();
        when(appointmentService.findById(appointmentId)).thenReturn(appointment);

        // Act
        String viewName = appointmentController.showUpdateForm(appointmentId, model);

        // Assert
        assertEquals("appointments/addAppointment", viewName);
        verify(model).addAttribute("appointmentsdetails", appointment);
    }

    @Test
    public void testDeleteAppointment() {
        // Arrange
        int appointmentId = 1;

        // Act
        String viewName = appointmentController.deleteappointment(appointmentId);

        // Assert
        assertEquals("redirect:/appointments/list", viewName);
        verify(appointmentService).deleteById(appointmentId);
    }
}

