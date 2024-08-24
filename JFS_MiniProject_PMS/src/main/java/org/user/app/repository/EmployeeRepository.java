package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

}
