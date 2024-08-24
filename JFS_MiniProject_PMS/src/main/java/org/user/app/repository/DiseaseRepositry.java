package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Disease;


public interface DiseaseRepositry extends JpaRepository<Disease, Integer>
{

}
