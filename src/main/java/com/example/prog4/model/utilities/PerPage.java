package com.example.prog4.model.employee.utilities;

import com.example.prog4.model.employee.exception.BadRequestException;
import lombok.Getter;

public record PerPage(@Getter Integer perPage) {
    public static final int MAX_PER_PAGE = 500;

    public PerPage(Integer perPage) {
        if (perPage > MAX_PER_PAGE) {
            throw new BadRequestException("Page size must be < " + MAX_PER_PAGE);
        } else {
            this.perPage = perPage;
        }
    }
}
