package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import dto.logindto;
import dto.tokendto;
import entity.EmployeeTable;
import entity.SignupTable;
import service.EmployeeService;
@RestController
@CrossOrigin(origins = "http://localhost:3333")
public class EmployeeLogin {
	@Autowired
	private EmployeeService employe;
	@PostMapping(path="/employee", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void employeesignup(@RequestBody EmployeeTable employee) {
		employe.addemployee(employee);	
	}
	
	@PostMapping(path="/employeelogin",produces=MediaType.APPLICATION_JSON_VALUE)
	public tokendto getemployee(@RequestBody logindto login) throws JsonProcessingException {
		return employe.getdetails(login);
		
	}
	@GetMapping(path="/getemployeedetails/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
	public EmployeeTable getemployeedetails(@PathVariable String email) {
		return employe.getprofile(email);
		
	}
	
	@PostMapping(path="/employeeupdate/{email}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody EmployeeTable employee,@PathVariable String email) {
		employe.update(employee,email);
	}

	
	
}
