package Dao;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import Exception.EmailAlreadyExist;
import entity.SignupTable;



public class SignupDao extends CreateFactory {
	
	
	public void adduser(SignupTable signup) {
	
			System.out.println("this is add user");
	      Session session=CreateFactory.setsession().openSession();
	      Transaction tx=session.beginTransaction();
	      session.save(signup);
	      tx.commit();
	      session.close();
	      CreateFactory.closefactory();
		
	
	}

	public SignupTable getuser(String mail) {
		Session session=setsession().openSession();
		SignupTable user=(SignupTable) session.get(SignupTable.class, mail);
		session.close();
		CreateFactory.closefactory();
		return user;
	}

	public void updateuser(SignupTable signup,String mail) {
		// TODO Auto-generated method stub
		Session session=CreateFactory.setsession().openSession();
		Transaction tx=session.beginTransaction();
		System.out.println("before getting the usere");
		SignupTable user=(SignupTable) session.get(SignupTable.class,mail);
		System.out.println("we got the usere");
		user.setEmail(signup.getEmail());
		user.setPhone(signup.getPhone());
		user.setFirstname(signup.getFirstname());
		user.setLastname(signup.getLastname());
		if(signup.getImage()!=null) {
			user.setImage(signup.getImage());
		}
		user.setPassword(signup.getPassword());
		session.update(user);
		tx.commit();
		session.close();
		CreateFactory.closefactory();
		
		
	}

}
