package com.example.prog4.cnaps.repository;

import com.example.prog4.cnaps.entity.CnapsEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnapsEmployeeRepository extends JpaRepository<CnapsEmployee, String> {
    CnapsEmployee getByEndToEndId(String endToEndId);
}
