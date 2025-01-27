package org.user.app.service;

import org.user.app.repository.DiseaseRepositry;
import org.user.app.model.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService
{
    private DiseaseRepositry diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepositry diseaseRepository)
    {
        this.diseaseRepository = diseaseRepository;
    }



    public List<Disease> getAllDiseases()
    {
        List<Disease> diseaseList = diseaseRepository.findAll();
        return diseaseList;
    }

    public void save(Disease disease)
    {
        diseaseRepository.save(disease);
    }

    public Disease findById(int id)
    {
        Disease newDisease =null;
        Optional<Disease> disease = diseaseRepository.findById(id);
        if(disease.isPresent())
        {
            newDisease = disease.get();
        }
        return newDisease;
    }


    public void deleteById(int id)
    {
        diseaseRepository.deleteById(id);
    }

}

