package controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import controller.EmployeeLogin;
import entity.EmployeeTable;
import service.EmployeeService;
@RunWith(MockitoJUnitRunner.class)
public class EmployeeLoginTest {
	@Mock
	private EmployeeService service;
	@InjectMocks
	private EmployeeLogin controller;
	private EmployeeTable employee;
	@Before
	public void table() {
		this.employee=new EmployeeTable();
	}
	
	@Test
	public void employeesignup() {
	
		controller.employeesignup(this.employee); 
	    verify(service).addemployee(this.employee);
		
	}
	
//	@Test
//	public void getemployee() {
//		
//		when(service.getdetails("new@employee.com")).thenReturn(this.employee);
//		
//		assertEquals(controller.getemployee("new@employee.com"), this.employee);
//		
//	}


}
