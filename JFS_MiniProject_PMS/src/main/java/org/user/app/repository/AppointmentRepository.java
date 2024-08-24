package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Appointments;


public interface AppointmentRepository extends JpaRepository<Appointments, Integer>
{

}

