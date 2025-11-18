package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    List<Employee> findByName(String name);
    int insert(Employee employee);
    int update(Employee employee);
    int delete(Long id);
    void truncate();
}
