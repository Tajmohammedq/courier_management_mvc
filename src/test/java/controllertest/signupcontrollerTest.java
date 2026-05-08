package controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.signupcontroller;
import entity.SignupTable;
import service.SignupService;
@RunWith(MockitoJUnitRunner.class)
public class signupcontrollerTest {
	
	@Mock
	private SignupService service;
	@InjectMocks
	private signupcontroller controller;

	
	
	@Test
	public void signup() {
		SignupTable signup=new SignupTable();
		controller.signup(signup);
	    verify(service).adduser(signup);
	}
//	@Test
//	public void  login() {
//		String email="new@gmail.com";
//		SignupTable signup=new SignupTable();
//		
//		when(service.getuser(email)).thenReturn(signup);
//		assertEquals(controller.login(email),signup);
//	}
	
	@Test
	public void update() {
		String email="new@gmail.com";
		SignupTable signup=new SignupTable();
		controller.update(signup,email);
	    verify(service).update(signup, email); 
	}

}
