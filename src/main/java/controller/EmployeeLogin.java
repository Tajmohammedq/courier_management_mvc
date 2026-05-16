package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import Exception.Emailnotexist;
import dto.ImageUploadResponse;
import dto.logindto;
import dto.tokendto;
import entity.EmployeeTable;
import entity.SignupTable;
import service.CloudinaryImageService;
import service.EmployeeService;
@RestController
@CrossOrigin(origins = "http://localhost:3333")
public class EmployeeLogin {
	@Autowired
	private EmployeeService employe;
	@Autowired
	private CloudinaryImageService cloudinaryImageService;
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

	@PostMapping(path="/employee-profile-image/{email}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ImageUploadResponse uploadEmployeeProfileImage(
			@PathVariable String email,
			@RequestParam("file") MultipartFile file) {
		EmployeeTable employee = employe.getprofile(email);
		if (employee == null) {
			throw new Emailnotexist("Looks like this Employeeemail is not present in our database");
		}

		String imageUrl = cloudinaryImageService.uploadProfileImage(file, email);
		return new ImageUploadResponse(imageUrl);
	}

	
	
}
