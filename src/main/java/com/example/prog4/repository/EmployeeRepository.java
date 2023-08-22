package com.example.prog4.repository;

import com.example.prog4.repository.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("employeeEntityManagerFactory")
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
