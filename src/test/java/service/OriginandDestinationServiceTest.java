package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import Dao.OriginandDestinationDao;
import entity.OriginAndDestinationData;
import entity.SaveOrders;
@RunWith(MockitoJUnitRunner.class)
public class OriginandDestinationServiceTest {
	@Mock
	private OriginandDestinationDao dao;
	@InjectMocks
	private OriginandDestinationServices service;
	private List<OriginAndDestinationData> Data;
	
	@Before
	public void add() {
		this.Data=new ArrayList<>();
		OriginAndDestinationData data=new OriginAndDestinationData("Bnaglore", "Hyderabed",50, 100);
		this.Data.add(data);
	}
	@Test
	public void getorigindata(){
		when(dao.getorigindata()).thenReturn((List<OriginAndDestinationData>) this.Data);
		assertEquals(service.getorigindata(), this.Data);
	}
	@Test
	public void getdestinationdata() {
		
		try {
		List<OriginAndDestinationData> s=new ArrayList<>();
		
		when(dao.getorigindata()).thenReturn( s);
		
			
		assertEquals(service.getdestinationdata("hyd"), s);
		}
		catch(Exception e) {
			assertEquals("NO DATA FOUND IN OUR DATABASE", e.getMessage());
			
		}
	
		
	}
	@Test
	public void checkorigin() {
		Map<String,String> m=new HashMap<>();
		m.put("hyderabed","Delhi");
		when(dao.checkorigin("hyd","delhi")).thenReturn(m);
		assertEquals(service.checkorigin("hyd","delhi"), m);
		
	}
	@Test
	public void savedata() {
		try {
			SaveOrders order=new SaveOrders();
			
			    service.savedata(order);    
			    verify(dao).savedata(order);
		}
		catch(Exception e) {
			assertEquals("Data does not satisfy the mandatory fields", e.getMessage());
			
		}
	}
	
	

}
