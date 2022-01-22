package com.emlakjet.exam.data.service;

import com.emlakjet.exam.data.entity.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService(@Autowired EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Employee> get(Integer id) {
        return repository.findById(id);
    }

    public Employee update(Employee entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<Employee> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
