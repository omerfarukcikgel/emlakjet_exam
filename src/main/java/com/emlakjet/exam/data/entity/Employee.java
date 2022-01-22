package com.emlakjet.exam.data.entity;

import com.emlakjet.exam.data.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;


@Entity
public abstract class Employee extends AbstractEntity {

    private String name;
    private String surName;
    private String email;
    private LocalDate beginDate;
    protected Integer employeeType;
    private BigDecimal price = BigDecimal.ZERO;
 
	public Employee(String name, String surName, String email, LocalDate beginDate, Integer employeeType,
			BigDecimal price) {
		super();
		this.name = name;
		this.surName = surName;
		this.email = email;
		this.beginDate = beginDate;
		this.employeeType = employeeType;
		this.price = price;
	}
	
	public Employee() {
	}

	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
    public Integer getEmployeeType() {
        return employeeType;
    }
    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
