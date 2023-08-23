package com.example.prog4.employee.controller.validator;

import com.example.prog4.employee.controller.validator.utils.StringValidator;
import com.example.prog4.model.exception.BadRequestException;
import com.example.prog4.employee.entity.Position;

public class PositionValidator {
    public void validate(Position position){
        StringBuilder error = new StringBuilder();

        if(StringValidator.isNotNullAndNotBlank(position.getName())){
            error.append("Position name is mandatory");
        }

        if(!error.isEmpty()){
            throw new BadRequestException(error.toString());
        }
    }
}
