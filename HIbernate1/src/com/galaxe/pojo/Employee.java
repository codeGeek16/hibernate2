package com.galaxe.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@ManyToOne 
	private Address addr;
   /*private Address address;*/

   @Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", addr=" + addr + "]";
	}

public Employee() {}
   
   public Address getAddr() {
	return addr;
}

public void setAddr(Address addr) {
	this.addr = addr;
}

public Employee(String fname, String lname, int salary,Address addr) {
      this.firstName = fname;
      this.lastName = lname;
      this.salary = salary;
      this.addr = addr;
     /* this.address = address;*/
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

   /*public Address getAddress() {
      return address;
   }
   
   public void setAddress( Address address ) {
      this.address = address;
   }*/
}
