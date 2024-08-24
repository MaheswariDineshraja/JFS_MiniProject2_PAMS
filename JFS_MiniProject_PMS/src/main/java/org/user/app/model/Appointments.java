package org.user.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "appointmentsdetails")
public class Appointments {
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  @Column(name="patient_name")
  private String patientName;
  @Column(name="father_name")
  private String fatherName;
  @Column(name="gender")
  private String gender;
  @Column(name="cnic")
  private String cnic;
  @Column(name="date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date appdate;
  @Column(name="time")
  private String apptime;
  @Column(name="details")
  private String details;
  
  
  @ManyToMany(fetch = FetchType.LAZY,
          cascade = {CascadeType.DETACH,CascadeType.MERGE,
                  CascadeType.PERSIST,CascadeType.REFRESH})
  @JoinTable(name = "appointments_patient",
          joinColumns = @JoinColumn(name = "appointment_id"),
          inverseJoinColumns = @JoinColumn(name = "patient_id"))
  private List<Patient> patientList;


   
  
  

}
