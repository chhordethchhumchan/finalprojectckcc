package AdvanceProject.HotelReservation;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import restaurant.Food;
import restaurant.Tables;


public class MainPrograme {
	
	private List<String> listTableFree = new ArrayList<String>();
	private List<String> getListTableReserved = new ArrayList<String>();
	private String[] listTableIDFree;
	int reservationID;
	

	public static void main(String[] args) {
		
		MainPrograme m = new MainPrograme();
		m.retrieveFoodTest();
	}
	
	
	public List<Tables> getIdDataFromTable() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			
			
			session.beginTransaction();
			Query resultTable= session.createQuery("from Tables");
			
			List<Tables> tableList  = resultTable.getResultList();
			 
			return tableList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
		return null;
		
	
	}
	//	-----------------------Get data From Table-------------------------------------------------
	public void getDataFromTable() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			
			session.beginTransaction();
			List table = new ArrayList();
			table = session.createQuery("from tables").getResultList(); 
			
			
			System.out.println(table);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
	
	}
//	-----------------------Create Table Model-------------------------------------------------
	public void createTable(int numSeat,String id,Boolean status ) {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Tables.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			Tables tableObject = new Tables(numSeat, id, status);
			session.beginTransaction();
			session.save(tableObject);
			
			session.getTransaction().commit();
			System.out.println("Inserted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factoryStudent.close();
		}
		
	
	}
	
	//	-----------------------Create Food Model-------------------------------------------------
	public void createFood (String id ,String name,double price) {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			Food food = new Food(id, name,price);
			session.beginTransaction();
			session.save(food);
			
			session.getTransaction().commit();
			
			System.out.println("Inserted!!!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			factoryStudent.close();
		}
		
	
	
	}
	

	
	public void retrieveFoodTest() {
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			
			session.beginTransaction();
			Query queryResult = session.createQuery("from Food");
			
			List<Food> foodList  = queryResult.getResultList();

			for (int i = 0; i < foodList.size(); i++) {
				Food food =  (Food) foodList.get(i);
				
				System.out.println(food.toString());
				
			}
			
			System.out.println("Database contents delivered...");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
	

}
