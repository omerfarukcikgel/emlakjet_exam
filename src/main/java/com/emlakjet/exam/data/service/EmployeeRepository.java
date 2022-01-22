package com.emlakjet.exam.data.service;

import com.emlakjet.exam.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}