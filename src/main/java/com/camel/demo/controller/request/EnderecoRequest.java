package com.camel.demo.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EnderecoRequest {

    @NotBlank
    private String cep;
}
