package controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import controller.OriginAndDestination;
import entity.OriginAndDestinationData;
import entity.SaveOrders;
import service.OriginandDestinationServices;

@RunWith(MockitoJUnitRunner.class)
public class OriginAndDestinationTest {
	@Mock
	private OriginandDestinationServices service;
	@InjectMocks
	private OriginAndDestination controller;
	private List<OriginAndDestinationData> Data;
	
	@Before
	public void add() {
		this.Data=new ArrayList<>();
		OriginAndDestinationData data=new OriginAndDestinationData("Bnaglore", "Hyderabed",50, 100);
		this.Data.add(data);
	}
	
	@Test
	public void getdata(){
		when(service.getorigindata()).thenReturn((List<OriginAndDestinationData>) this.Data);
		assertEquals(controller.getdata(), this.Data);
	}
	@Test
	public void getdestinationdata(){
		Set<String> s=new HashSet<>();
		s.add("hydrabad");
		s.add("banglore");
		when(service.getdestinationdata("hyd")).thenReturn((Set<String>) s);
		assertEquals(controller.getdestinationdata("hyd"), s);
		
	}
	@Test
	public void checkorigin(){
		Map<String,String> m=new HashMap<>();
		m.put("hyderabed","Delhi");
		when(service.checkorigin("hyd","delhi")).thenReturn(m);
		assertEquals(controller.checkorigin("hyd","delhi"), m);
	}
	@Test
	public void saveorder() {
		SaveOrders order=new SaveOrders();	 
	    controller.saveorder(order);    
	    verify(service).savedata(order);
		
	}

	
	
	
}
