package com.example.demo.controller.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer code;

    private String message;

    private String cause;
}
