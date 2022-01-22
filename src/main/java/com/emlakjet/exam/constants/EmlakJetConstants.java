package com.emlakjet.exam.constants;

import java.math.BigDecimal;

public class EmlakJetConstants 
{

    // Employee Types
    public final static int EMPLOYEE_TYPE_DRIVER = 1;
    public final static int EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST = 2;
    public final static int EMPLOYEE_TYPE_SALESEXPERT = 3;
    public final static int EMPLOYEE_TYPE_SOFTWARESPECIALIST = 4;
    
    
    // Employee Coefficient By Types
    public final static BigDecimal EMPLOYEE_COEF_DRIVER = new BigDecimal(1.7);
    public final static BigDecimal EMPLOYEE_COEF_ACCOUNTINGSPECIALIST= new BigDecimal(1.2);
    public final static BigDecimal EMPLOYEE_COEF_SALESEXPERT = new BigDecimal(1.3);
    public final static BigDecimal EMPLOYEE_COEF_SOFTWARESPECIALIST = new BigDecimal(1.5);

}
