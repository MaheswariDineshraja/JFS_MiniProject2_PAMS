package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>
{

}
