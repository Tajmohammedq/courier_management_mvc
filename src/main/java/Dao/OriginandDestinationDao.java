package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entity.OriginAndDestinationData;
import entity.SaveOrders;



public class OriginandDestinationDao {
	
	public List<OriginAndDestinationData> getorigindata(){
		System.out.println("came into dao");
		Session s=CreateFactory.setsession().openSession();


		
		List<OriginAndDestinationData> list=s.createCriteria(OriginAndDestinationData.class).list();
		s.close();
		CreateFactory.closefactory();
			
		
		
		return list;
		
	}

	public Set<String> getdestinationdata(String input) {
		System.out.println("came into dao");
		List<String> data=new ArrayList<>();
		Session s=CreateFactory.setsession().openSession();
		
		List<OriginAndDestinationData> list=s.createCriteria(OriginAndDestinationData.class).list();
		for(OriginAndDestinationData d:list) {
			
			if(d.getTo_location().trim().substring(0,input.length()).toLowerCase().equals(input.trim().toLowerCase())) {
				data.add(d.getTo_location().trim());
			}
			
		}
		s.close();
		CreateFactory.closefactory();
		HashSet<String> unique=new HashSet<>();
     for(String a : data) {
    	 System.out.println(a);
    	 unique.add(a);
     }
		System.out.println(unique.size());
		
		
		
		return new HashSet<>(data);
		
	}

	public Map<String,String> checkorigin(String input,String input2) {
		Map<String,String> data=new HashMap<>();
		Session s=CreateFactory.setsession().openSession();

		
		List<OriginAndDestinationData> list=s.createCriteria(OriginAndDestinationData.class).list();
		for(OriginAndDestinationData d:list) {
			System.out.println(d.getFrom_location());
			
			if(d.getFrom_location().trim().toLowerCase().equals(input.trim().toLowerCase())) {
				for(OriginAndDestinationData k:list) {
					if(k.getTo_location().trim().toLowerCase().equals(input2.trim().toLowerCase())) {
						for(OriginAndDestinationData f:list) {
							System.out.println(f.getTo_location().trim().toLowerCase());
							System.out.println(f.getFrom_location().trim().toLowerCase());
							if(f.getFrom_location().trim().toLowerCase().equals(input.trim().toLowerCase())&&f.getTo_location().trim().toLowerCase().equals(input2.trim().toLowerCase())) {
								data.put("message","true");
								data.put("price", String.valueOf(f.getPrice()));
								return data;
							}
							
							
						}
						s.close();
						CreateFactory.closefactory();
						data.put("message","We cannot deliver between these location");
						return data;
					}
					
				}
				s.close();
				CreateFactory.closefactory();
				data.put("message","We cannot deliver to this location");
				return data;			
				
			}
			
		}
		s.close();
		CreateFactory.closefactory();
		data.put("message","We cannot take order from this location");
		return data;
		

		
		
		
		
	}

	public void savedata(SaveOrders saveorders) {
		System.out.println("saving data");
		Session s=CreateFactory.setsession().openSession();
		Transaction tx=s.beginTransaction();
		s.save(saveorders);
		tx.commit();
		s.close();
		CreateFactory.closefactory();
		
		
		
	}
}
