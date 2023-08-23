package com.example.prog4.employee.controller.mapper;

import com.example.prog4.employee.entity.enums.Sex;
import org.springframework.stereotype.Component;

@Component
public class SexMapper {
    public Sex toDomain(String sex){
        try {
            return Sex.valueOf(sex);
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
