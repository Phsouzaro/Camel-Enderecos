package com.example.demo.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EnderecoRequest {

    @NotBlank
    private String cep;
}
