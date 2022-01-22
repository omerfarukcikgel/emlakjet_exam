package com.emlakjet.exam.data.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.emlakjet.exam.constants.EmlakJetConstants;

@Entity
public class Driver extends Employee{
	
	private String carBrand;
	private String carModel;

	public Driver(String name, String surName, String email, LocalDate beginDate, Integer employeeType,
			BigDecimal price) {
		super(name, surName, email, beginDate, employeeType, price);
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_DRIVER;
	}
	
	public Driver() {
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_DRIVER;
	}
	
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
    
}
