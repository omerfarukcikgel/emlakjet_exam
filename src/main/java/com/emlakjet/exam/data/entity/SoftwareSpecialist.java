package com.emlakjet.exam.data.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.emlakjet.exam.constants.EmlakJetConstants;

@Entity
public class SoftwareSpecialist extends Employee{
	
	private String softwareRole;
	private String projectName;
	
	public SoftwareSpecialist(String name, String surName, String email, LocalDate beginDate, Integer employeeType,
			BigDecimal price) {
		super(name, surName, email, beginDate, employeeType, price);
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_SOFTWARESPECIALIST;
	}
	
	public SoftwareSpecialist() {
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_SOFTWARESPECIALIST;
	}
	
	public String getSoftwareRole() {
		return softwareRole;
	}
	public void setSoftwareRole(String softwareRole) {
		this.softwareRole = softwareRole;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	 
}
