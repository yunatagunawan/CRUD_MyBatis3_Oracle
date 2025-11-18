package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Employee> findAll() {
        return mapper.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public int insert(Employee employee) {
        return mapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return mapper.update(employee);
    }

    @Override
    public int delete(Long id) {
        return mapper.delete(id);
    }

    @Override
    public void truncate() {
        mapper.truncate();
    }
}
