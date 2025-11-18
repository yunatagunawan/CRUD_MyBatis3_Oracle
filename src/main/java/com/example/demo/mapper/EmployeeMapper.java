package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
    Employee findById(Long id);
    List<Employee> findByName(@Param("name") String name);
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Employee employee);
    int update(Employee employee);
    int delete(Long id);
    void truncate();
}
