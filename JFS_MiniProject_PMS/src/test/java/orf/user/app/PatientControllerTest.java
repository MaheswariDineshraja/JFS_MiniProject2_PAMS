package orf.user.app;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.user.app.controller.PatientController;
import org.user.app.model.Patient;
import org.user.app.service.PatientService;
import org.user.app.service.DoctorService;
import org.user.app.service.DiseaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private DiseaseService diseaseService;

    private Patient testPatient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Initialize test patient data
        testPatient = new Patient();
        testPatient.setId(1);
        testPatient.setName("John Doe");
        testPatient.setFatherName("Richard Roe");
        testPatient.setGender("Male");
        testPatient.setCNIC("1234567890123");
        testPatient.setDateOfBirth(new Date());
        testPatient.setDiseaseHistory("Flu");
        testPatient.setPrescription("Rest");
    }

    @Test
    public void testListPatients() throws Exception {
        List<Patient> patients = Arrays.asList(testPatient);
        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(MockMvcRequestBuilders.get("/patients/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patients/list-patients"))
                .andExpect(MockMvcResultMatchers.model().attribute("patients", patients))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetPatientForm() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(new ArrayList<>()); // Mock the doctors list

        mockMvc.perform(MockMvcRequestBuilders.get("/patients/addPatient"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patients/addPatient"))
                .andExpect(MockMvcResultMatchers.model().attribute("patient", new Patient()))
                .andExpect(MockMvcResultMatchers.model().attribute("doctorList", new ArrayList<>()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testSavePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/patients/save")
                        .param("patientName", testPatient.getName())
                        .param("fatherName", testPatient.getFatherName())
                        .param("gender", testPatient.getGender())
                        .param("cnic", testPatient.getCNIC())
                        .param("dateOfBirth", "2024-08-24") // Format should match @DateTimeFormat
                        .param("diseaseHistory", testPatient.getDiseaseHistory())
                        .param("prescription", testPatient.getPrescription()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/patients/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        when(patientService.findById(1)).thenReturn(testPatient);
        when(doctorService.getAllDoctors()).thenReturn(new ArrayList<>()); // Mock the doctors list

        mockMvc.perform(MockMvcRequestBuilders.get("/patients/showFormForUpdate")
                        .param("patientId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patients/addPatient"))
                .andExpect(MockMvcResultMatchers.model().attribute("patient", testPatient))
                .andExpect(MockMvcResultMatchers.model().attribute("doctorList", new ArrayList<>()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeletePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patients/delete")
                        .param("patientId", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/patients/list"))
                .andDo(MockMvcResultHandlers.print());

        verify(patientService, times(1)).deleteById(1);
    }
}
