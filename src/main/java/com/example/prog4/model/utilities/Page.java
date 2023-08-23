package com.example.prog4.model.employee.utilities;

import com.example.prog4.model.employee.exception.BadRequestException;
import lombok.Getter;

public record Page(@Getter Integer page) {
    public Page(Integer page) {
        if (page < 1) {
            throw new BadRequestException("Page cannot be < 1");
        } else {
            this.page = page - 1;
        }
    }
}
