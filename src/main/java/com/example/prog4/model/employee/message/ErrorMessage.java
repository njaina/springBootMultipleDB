package com.example.prog4.model.employee.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String message;
    private int code;
}
