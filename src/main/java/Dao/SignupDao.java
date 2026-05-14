package Dao;

import java.sql.SQLIntegrityConstraintViolationException;

import controller.signupcontroller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import entity.SignupTable;
import org.slf4j.LoggerFactory;


public class SignupDao extends CreateFactory {

	private static final Logger logger =
			LoggerFactory.getLogger(SignupDao.class);
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
		logger.info("Fetching user from DB");
		Session session=setsession().openSession();
		SignupTable user=(SignupTable) session.get(SignupTable.class, mail);
		session.close();
		CreateFactory.closefactory();
		logger.info("Found user from from DB");
		return user;
	}

	public void updateuser(SignupTable signup,String mail) {
		// TODO Auto-generated method stub
		Session session=CreateFactory.setsession().openSession();
		Transaction tx=session.beginTransaction();
		logger.info("before getting the usere");
		SignupTable user=(SignupTable) session.get(SignupTable.class,mail);
		logger.info("we got the usere");
		user.setEmail(signup.getEmail());
		user.setPhone(signup.getPhone());
		user.setFirstname(signup.getFirstname());
		user.setLastname(signup.getLastname());
		if(signup.getImage()!=null && !signup.getImage().trim().isEmpty()) {
			user.setImage(signup.getImage());
		}
		if(signup.getPassword()!=null && !signup.getPassword().trim().isEmpty()) {
			user.setPassword(signup.getPassword());
		}
		session.update(user);
		tx.commit();
		session.close();
		CreateFactory.closefactory();
		
		
	}

}
