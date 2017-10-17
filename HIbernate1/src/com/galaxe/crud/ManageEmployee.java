package com.galaxe.crud;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.galaxe.pojo.Address;
/*import com.galaxe.pojo.Address;*/
import com.galaxe.pojo.Employee;

public class ManageEmployee {
   private static SessionFactory factory; 
   
/*   public static SessionFactory getSessionFactory() {
       if (factory == null) {
           Configuration configuration = new Configuration().configure();
           StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
           registry.applySettings(configuration.getProperties());
           MetadataSources metadataSources = new MetadataSources();
           metadataSources.addAnnotatedClass(Employee.class);
           Metadata metadata = metadataSources.buildMetadata(registry.build());
           factory = metadata.buildSessionFactory();           
       }
        
       return factory;//after this put hibernat.cfg.xml directly under src folder
   }*/
   
   public static void main(String[] args) {
      
      try {
    	 Configuration config = new Configuration();
    	 
    	  File f = new File("hibernate.cfg.xml");
    	  config.configure(f);
    	  config.addAnnotatedClass(Employee.class);
    	  config.addAnnotatedClass(Address.class);
    	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	
    	  /*Configuration config = new Configuration();*/
    	 //config.configure("D:/eclipse/practice/HIbernate1/hibernate.cfg.xml");
         factory = config.buildSessionFactory(serviceRegistry);
         
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageEmployee ME = new ManageEmployee();

      /* Let us have one address object */
      Address address = ME.addAddress("Kondapur","Hyderabad","AP","532");

      /* Add employee records in the database */
      Integer empID1 = ME.addEmployee("Manoj", "Kumar", 4000,address);

      /* Let us have one address object */
      Address address2 = ME.addAddress("BCS","Shimla","HP","648");
      
      /* Add another employee record in the database */
      Integer empID2 = ME.addEmployee("Dilip", "Kumar", 3000,address2);

      /* List down all the employees */
     /* ME.listEmployees();

       Update employee's salary records 
      ME.updateEmployee(empID1, 5000);

       Delete an employee from the database 
      ME.deleteEmployee(empID2);

       List down all the employees 
      ME.listEmployees();*/

   }

   /* Method to add an address record in the database */
   public Address addAddress(String street, String city, String state, String zipcode) {
      Session session = factory.openSession();
      Transaction tx = null;
      Integer addressID = null;
      Address address = null;
      
      try {
         tx = session.beginTransaction();
         address = new Address(street, city, state, zipcode);
         addressID = (Integer) session.save(address); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return address;
   }

   /* Method to add an employee record in the database */
   public Integer addEmployee(String fname, String lname, int salary,Address addr){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      
      try {
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary,addr);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return employeeID;
   }

   /* Method to list all the employees detail */
   public void listEmployees( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM EMPLOYEE").list(); 
         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary());
            /*Address add = employee.getAddress();*/
            System.out.println("Address ");
            /*System.out.println("\tStreet: " +  add.getStreet());
            System.out.println("\tCity: " + add.getCity());
            System.out.println("\tState: " + add.getState());
            System.out.println("\tZipcode: " + add.getZipcode());*/
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to update salary for an employee */
   public void updateEmployee(Integer EmployeeID, int salary ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
         session.update(employee);
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to delete an employee from the records */
   public void deleteEmployee(Integer EmployeeID){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
         session.delete(employee); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}