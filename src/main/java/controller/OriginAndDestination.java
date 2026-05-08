package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.OriginAndDestinationData;
import entity.SaveOrders;
import service.OriginandDestinationServices;

@RestController
@CrossOrigin(origins = "http://localhost:3333")
public class OriginAndDestination {
	@Autowired
	private OriginandDestinationServices service;
	
	@GetMapping(path="/origindata",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OriginAndDestinationData> getdata(){
		System.out.println("came into controller");
		return service.getorigindata();
	}
	@GetMapping(path="/destinationdata/{input}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Set<String> getdestinationdata(@PathVariable String input){
		System.out.println("came to destination");
		return service.getdestinationdata(input);
	}
	@GetMapping(path="/checkorigin/{input}/{input2}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> checkorigin(@PathVariable String input,@PathVariable String input2){
		Map<String,String> map=new HashMap<>();
		try {
			return service.checkorigin(input,input2);
		}
		catch(Exception e) {
			e.printStackTrace();
			map.put("message", e.getMessage());
			return map;
		}
		
	}
	@PostMapping(path="/saveorder", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveorder(@RequestBody SaveOrders saveorders) {
		System.out.println("--- Calling ----");
		service.savedata(saveorders);
		
	}


}
