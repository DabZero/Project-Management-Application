package com.jrp.pma.api.controllers;

import java.util.List;

//import javax.validation.Valid;

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

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	//Return a List of all employees
	@GetMapping
	public List<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") long id) {
		
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		//By default this method returns the created employee
		return empRepo.save(employee);	
	}
	
	@PutMapping(consumes ="application/json")
	public Employee update(@RequestBody Employee employee) {
		
		return empRepo.save(employee);
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") long id, 
							@RequestBody Employee patchEmployee) {
		
		Employee emp = empRepo.findById(id).get();
		
		if (patchEmployee.getEmail() != null ) 		{emp.setEmail(patchEmployee.getEmail());}
		if (patchEmployee.getFirstName() != null ) 	{emp.setFirstName(patchEmployee.getFirstName());}
		if (patchEmployee.getLastName() != null ) 	{emp.setLastName(patchEmployee.getLastName());}
		
		return empRepo.save(emp);
	}
	
	@DeleteMapping(path="/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		
		try {
		empRepo.deleteById(id);
//		Employee emp = empRepo.findById(id).get();
//		empRepo.delete(emp);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Record has already been deleted: " + e);
		}
	}
}










