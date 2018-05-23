package com.etnetera.hr.service;

import java.util.LinkedList;
import java.util.List;

import com.etnetera.hr.data.FrameworkRequestDTO;
import com.etnetera.hr.data.FrameworkSpecification;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FrameworkService {

    @Autowired
    JavaScriptFrameworkRepository repository;

    public List<JavaScriptFramework> updateFrameworks(FrameworkRequestDTO requestDTO) {
        List<JavaScriptFramework> frameworksToUpdate = findFrameworks(requestDTO);
        List<JavaScriptFramework> updated = new LinkedList<JavaScriptFramework>();

        for(JavaScriptFramework framework : frameworksToUpdate) {

            updated.add(updateFramework(framework, requestDTO));
        }
        return updated;
    }

    public List<JavaScriptFramework> findFrameworks(FrameworkRequestDTO requestDTO) {
        Specification<JavaScriptFramework> searchSpec = FrameworkSpecification.dynamicQuery(requestDTO);

        //TODO kdyz neni zadane zadne vyhledavaci kriterium, vrati vsechny(resila bych spise validacema vstupniho jsonu)
        List<JavaScriptFramework> searchResult = repository.findAll(searchSpec);

        return searchResult;
    }


    private JavaScriptFramework updateFramework(JavaScriptFramework entity, FrameworkRequestDTO requestDTO) {
        if(requestDTO.getNameToSet() != null) {
            entity.setName(requestDTO.getNameToSet());
        }
        if(requestDTO.getVersionToUpdate() != null) {
            entity.setVersion(requestDTO.getVersionToUpdate());
        }
        if(requestDTO.getHypeLevelToSet() != null) {
            entity.setHypeLevel(requestDTO.getHypeLevelToSet());
        }
        if(requestDTO.getDepreciationDateToSet() != null) {
            entity.setDeprecationDate(requestDTO.getDepreciationDateToSet());
        }
        repository.save(entity);

        return entity;
    }

}
