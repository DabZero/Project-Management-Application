package com.jrp.pma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping
	public List<Project> getProjects(){
		
		return proRepo.findAll();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") long id) {
		
		return proRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project) {
		//By default this method returns the created object
		return proRepo.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	public Project update(@RequestBody Project project) {
		
		return proRepo.save(project);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Project partialUpdate(@PathVariable("id") long id, 
								@RequestBody Project patchProject) {
	
		//Find record to update
		Project proj = proRepo.findById(id).get();
	
		//Set each attribute of the existing record w/ the body that was passed 
		if (patchProject.getName() 	!= null) 	{proj.setName(patchProject.getName());}
		if (patchProject.getStage() != null)	{proj.setStage(patchProject.getStage());}
		if (patchProject.getDescription()!=null){proj.setDescription(patchProject.getDescription());}
		
		//save the object you just set/updated to the DB
		return proRepo.save(proj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		
		try {
		
		proRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Record has already been deleted: " + e);
		}
	}
	
}













