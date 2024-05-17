package com.camel.demo.mapper;

import com.camel.demo.domain.EnderecoDomain;
import com.camel.demo.controller.response.EnderecoResponse;
import com.camel.demo.controller.response.ErrorResponse;

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
