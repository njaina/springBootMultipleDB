package com.example.prog4.repository;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.entity.Employee;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepository {
    Employee findById(String id);
    List<Employee> findByCriteria(EmployeeFilter filter, Pageable pageable);
    Employee save(Employee employee);
}
