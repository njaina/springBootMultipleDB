package com.example.prog4.repository;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeRepositoryFacade {
    private final EmployeeRepositoryImpl employeeRepository;

    public Employee findById(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByCriteria(EmployeeFilter filter, Pageable pageable) {
        return employeeRepository.findByCriteria(filter, pageable);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    private Employee withCnaps(Employee employee) {
        return employeeRepository.withCnaps(employee);
    }
}