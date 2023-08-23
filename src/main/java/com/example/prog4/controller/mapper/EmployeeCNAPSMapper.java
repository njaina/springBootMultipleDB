package com.example.prog4.controller.mapper;

import com.example.prog4.repository.entity.cnaps.Employee;

public class EmployeeCNAPSMapper {
    public Employee toDomain (com.example.prog4.model.Employee employee){
        Employee toDomainForCNAPS = Employee.builder()
                .end_to_end_id(employee.getEnd_to_end_id())
                .build();
        return toDomainForCNAPS;
    }
}
