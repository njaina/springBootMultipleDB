package com.example.prog4.repository;

import com.example.prog4.cnaps.entity.CnapsEmployee;
import com.example.prog4.cnaps.repository.CnapsEmployeeRepository;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.dao.EmployeeManagerDao;
import com.example.prog4.repository.entity.Employee;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeJpaRepository employeeJpaRepository;
    private final CnapsEmployeeRepository cnapsEmployeeRepository;
    private final EmployeeManagerDao employeeManagerDao;
    @Override
    public Employee findById(String id) {
        Employee employee = employeeJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee(id=" + id + ") not found"));
        return withCnaps(employee);
    }

    @Override
    public List<Employee> findByCriteria(EmployeeFilter filter, Pageable pageable) {
        return employeeManagerDao.findByCriteria( filter.getLastName(),
                filter.getFirstName(),
                filter.getCountryCode(),
                filter.getSex(),
                filter.getPosition(),
                filter.getEntrance(),
                filter.getDeparture(),
                pageable).stream().map(this::withCnaps).toList();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    public Employee withCnaps(Employee employee) {
        CnapsEmployee associatedCnaps = cnapsEmployeeRepository.getByEndToEndId(employee.getId());
        if (associatedCnaps != null) {
            employee.setCnaps(associatedCnaps.getCnapsNumber());
        }
        return employee;
    }
}
