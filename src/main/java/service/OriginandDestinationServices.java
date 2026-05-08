package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Dao.OriginandDestinationDao;
import Exception.DataformatError;
import Exception.NoDataFound;
import entity.OriginAndDestinationData;
import entity.SaveOrders;

public class OriginandDestinationServices {
	private OriginandDestinationDao dao;
	public OriginandDestinationDao getDao() {
		return dao;
	}

	public void setDao(OriginandDestinationDao dao) {
		this.dao = dao;
	}

	public List<OriginAndDestinationData> getorigindata(){
		List<OriginAndDestinationData> list=new ArrayList<>(dao.getorigindata());
		System.out.println(list);
		if(list.size()==0) {
			throw new NoDataFound("NO DATA FOUND IN OUR DATABASE");
		}
		return dao.getorigindata();
	}

	public Set<String> getdestinationdata(String input) {
		List<OriginAndDestinationData> list=new ArrayList<>(dao.getorigindata());
		System.out.println(list);
		if(list.size()==0) {
			throw new NoDataFound("NO DATA FOUND IN OUR DATABASE");
		}
		return dao.getdestinationdata(input);
	}

	public Map<String,String> checkorigin(String input,String input2) {
		Map<String,String> map=dao.checkorigin(input, input2);
		
		if(map.size()==0) {
			
			throw new NoDataFound("NO DATA FOUND BETWEEN THIS ORIGIN AND DESTINATION");
		}
	
		return dao.checkorigin(input,input2);
	}

	public void savedata(SaveOrders saveorders) {
		if(saveorders.getEmail()==null||saveorders.getFrom_phone()==null||saveorders.getItem()==null) {
			throw new DataformatError("Data does not satisfy the mandatory fields");
		}
		else {
			dao.savedata(saveorders);
			
		}
		
		
	}

	

}
