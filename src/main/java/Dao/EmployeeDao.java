package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.EmployeeTable;
import entity.SignupTable;


public class EmployeeDao extends CreateFactory {

	public void adduser(EmployeeTable emp) {
		 Session session=CreateFactory.setsession().openSession();
	      Transaction tx=session.beginTransaction();
	      session.save(emp);
	      tx.commit();
	      session.close();
	      CreateFactory.closefactory();
		
	}

	

	public EmployeeTable get(String mail) {
		Session session=CreateFactory.setsession().openSession();
		EmployeeTable employe=(EmployeeTable) session.get(EmployeeTable.class,mail);
		session.close();
		CreateFactory.closefactory();
		return employe;
	}



	public void updateuser(EmployeeTable employee, String email) {
		Session session=CreateFactory.setsession().openSession();
		Transaction tx=session.beginTransaction();
		EmployeeTable user=(EmployeeTable) session.get(EmployeeTable.class,email);
		user.setEmail(employee.getEmail());
		user.setPhone(employee.getPhone());
		user.setFirstname(employee.getFirstname());
		user.setLastname(employee.getLastname());
		if(employee.getImage()!=null) {
			user.setImage(employee.getImage());
		}
		user.setPassword(employee.getPassword());
		session.update(user);
		tx.commit();
		session.close();
		CreateFactory.closefactory();
		
	}



	public EmployeeTable getemployeeprofile(String email) {
		Session session=setsession().openSession();
		EmployeeTable user=(EmployeeTable) session.get(EmployeeTable.class, email);
		session.close();
		CreateFactory.closefactory();
		return user;
	
	}
	

}
