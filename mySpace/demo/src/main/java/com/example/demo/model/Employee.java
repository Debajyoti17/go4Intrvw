package com.example.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="employee")
@DynamicUpdate(value=true)
public class Employee implements Serializable, Comparable<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1389795261414006425L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empId;
	private String name;
	private String address;
	private Integer salary;
	private Integer age;
	private String dept;
	private boolean status;
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	public Employee(Long empId, String name, String address, Integer salary, Integer age, String dept) {
		super();
		this.empId = empId;
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.age = age;
		this.dept = dept;
		this.status = true;
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public Employee() {
		super();
		this.status = true;
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", address=" + address + ", salary=" + salary + ", age="
				+ age + ", dept=" + dept + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
