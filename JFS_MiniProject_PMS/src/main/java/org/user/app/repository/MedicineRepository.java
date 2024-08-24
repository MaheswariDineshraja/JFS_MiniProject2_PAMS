package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, Integer>
{

}