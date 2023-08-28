package com.kadri.springboot.cruddemo.service;


import com.kadri.springboot.cruddemo.dao.EmployeeRepository;
import com.kadri.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;
    }

    @Override
    // @Transactional // Data JPA automatically provides this annotation
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    //@Transactional // Data JPA automatically provides this annotation
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
