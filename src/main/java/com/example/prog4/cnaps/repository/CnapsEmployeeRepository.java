package com.example.prog4.cnaps.repository;

import com.example.prog4.cnaps.entity.CnapsEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CnapsEmployeeRepository{
    List<CnapsEmployee> findAll();
    CnapsEmployee getByEndToEndId(String endToEndId);
}
