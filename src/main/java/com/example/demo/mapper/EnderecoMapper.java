package com.example.demo.mapper;

import com.example.demo.controller.response.EnderecoResponse;
import com.example.demo.controller.response.ErrorResponse;
import com.example.demo.domain.EnderecoDomain;

public class EnderecoMapper {

    public static EnderecoResponse mapEnderecoResponse(EnderecoDomain domain) {
        return EnderecoResponse.builder()
                .uf(domain.getUf())
                .cep(domain.getCep())
                .bairro(domain.getBairro())
                .complemento(domain.getComplemento())
                .localidade(domain.getLocalidade())
                .logradouro(domain.getLogradouro())
                .build();
    }

    public static EnderecoResponse mapEnderecoResponseError(ErrorResponse errorResponse) {
        return EnderecoResponse.builder()
                .errorResponse(errorResponse)
                .build();
    }
}
