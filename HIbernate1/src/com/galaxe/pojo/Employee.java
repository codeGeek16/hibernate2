package com.galaxe.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String firstName; 
	@Column
	private String lastName;
	@Column
	private int salary;
   private Address address;

   public Employee() {}
   
   public Employee(String fname, String lname, int salary ,Address address) {
      this.firstName = fname;
      this.lastName = lname;
      this.salary = salary;
      this.address = address;
   }
   
   public int getId() {
      return id;
   }
   
   public void setId( int id ) {
      this.id = id;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   
   public int getSalary() {
      return salary;
   }
   
   public void setSalary( int salary ) {
      this.salary = salary;
   }

   public Address getAddress() {
      return address;
   }
   
   public void setAddress( Address address ) {
      this.address = address;
   }
}
