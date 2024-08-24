package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Integer>
{

}
