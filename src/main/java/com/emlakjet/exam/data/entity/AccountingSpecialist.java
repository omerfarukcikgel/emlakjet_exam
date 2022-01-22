package com.emlakjet.exam.data.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.emlakjet.exam.constants.EmlakJetConstants;

@Entity
public class AccountingSpecialist extends Employee {

	private String usedApplicationName;
    private String certificateName;
    
	public AccountingSpecialist(String name, String surName, String email, LocalDate beginDate, Integer employeeType,
			BigDecimal price) {
		super(name, surName, email, beginDate, employeeType, price);
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST;
	}
	
	public AccountingSpecialist() {
		this.employeeType = EmlakJetConstants.EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST;
	}
	
	public String getUsedApplicationName() {
		return usedApplicationName;
	}
	public void setUsedApplicationName(String usedApplicationName) {
		this.usedApplicationName = usedApplicationName;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
    
}
