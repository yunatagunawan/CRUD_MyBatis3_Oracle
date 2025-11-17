package com.example.demo.service;

import com.example.demo.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    int insert(Employee employee);
    int update(Employee employee);
    int delete(Long id);
    void truncate();
}
