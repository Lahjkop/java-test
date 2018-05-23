package com.etnetera.hr.service;

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

    public void updateFrameworks(FrameworkRequestDTO requestDTO) {

        List<JavaScriptFramework> FrameworksToUpdate = findFrameworks(requestDTO);
        FrameworksToUpdate.forEach(entity -> updateFramework(entity, requestDTO));

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
            entity.setVersion(requestDTO.getVersion());
        }
        if(requestDTO.getHypeLevelToSet() != null) {
            entity.setHypeLevel(requestDTO.getHypeLevel().toString());
        }
        if(requestDTO.getDepreciationDateToSet() != null) {
            entity.setDeprecationDate(requestDTO.getDepreciationDateToSet());
        }
        repository.save(entity);

        return entity;
    }

}
