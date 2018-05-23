package com.etnetera.hr.controller;

import java.util.List;

import javax.validation.Valid;

import com.etnetera.hr.data.FrameworkRequestDTO;
import com.etnetera.hr.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

/**
 * Simple REST controller for accessing application logic.
 *  \
 * @author Etnetera
 *
 */
@RestController
@RequestMapping()
public class JavaScriptFrameworkController extends EtnRestController {

    private final JavaScriptFrameworkRepository repository;

    private final FrameworkService service;

    @Autowired
	public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository, FrameworkService service) {
		this.repository = repository;
		this.service = service;
	}

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addFramework(@Valid @RequestBody JavaScriptFramework javaScriptFramework) {

            repository.save(javaScriptFramework);

    }

    @RequestMapping("/remove")
    public void deleteFramework(@RequestBody FrameworkRequestDTO requestDTO) {

        repository.deleteAll(service.findFrameworks(requestDTO));
    }

    @RequestMapping("/search")
    public Iterable<JavaScriptFramework> searchFramework(@RequestBody FrameworkRequestDTO requestDTO) {

        return service.findFrameworks(requestDTO);
    }

    @RequestMapping("/update")
    public List<JavaScriptFramework> updateFramework(@RequestBody FrameworkRequestDTO javaScriptFramework) {

        return service.updateFrameworks(javaScriptFramework);
    }

}


	

