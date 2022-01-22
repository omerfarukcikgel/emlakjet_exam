package com.emlakjet.exam.data.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.emlakjet.exam.constants.EmlakJetConstants;

@Entity
public class SalesExpert extends Employee{
	

	private String workArea;
	private String manager;
	
	public SalesExpert(String name, String surName, String email, LocalDate beginDate, Integer employeeType,
			BigDecimal price) {
		super(name, surName, email, beginDate, employeeType, price);
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_SALESEXPERT;
	}
	
	public SalesExpert() {
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_SALESEXPERT;
	}
	
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	    
}
