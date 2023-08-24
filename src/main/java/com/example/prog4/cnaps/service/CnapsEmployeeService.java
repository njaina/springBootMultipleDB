package com.example.prog4.cnaps.service;

import com.example.prog4.cnaps.entity.CnapsEmployee;
import com.example.prog4.cnaps.repository.CnapsEmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CnapsEmployeeService {
    private final CnapsEmployeeRepository cnapsEmployeeRepository;

    public List<CnapsEmployee> getAll () {
        return cnapsEmployeeRepository.findAll();
    }
}
