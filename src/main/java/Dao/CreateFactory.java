package Dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateFactory {
	private static SessionFactory factory;
	public static SessionFactory setsession() {
		if (factory==null) {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.xml");
			factory=cfg.buildSessionFactory();
			return factory;
		}
		return factory;
		
	}
	
	public static void  closefactory() {
		
		if(factory!=null) {
			
			factory.close();
			factory=null;
		}
		
		
	}
	

}
