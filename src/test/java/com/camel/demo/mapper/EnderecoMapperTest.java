package com.camel.demo.mapper;

import com.camel.demo.controller.response.EnderecoResponse;
import com.camel.demo.controller.response.ErrorResponse;
import com.camel.demo.domain.EnderecoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnderecoMapperTest {

    private EnderecoDomain enderecoDomain;
    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        enderecoDomain = new EnderecoDomain();
        enderecoDomain.setUf("SP");
        enderecoDomain.setCep("12345");
        enderecoDomain.setBairro("Centro");
        enderecoDomain.setComplemento("Apto 123");
        enderecoDomain.setLocalidade("Sao Paulo");
        enderecoDomain.setLogradouro("Rua A");

        errorResponse = new ErrorResponse();
        errorResponse.setCode(400);
        errorResponse.setMessage("Error occurred");
    }

    @Test
    void shouldMapEnderecoResponseFromEnderecoDomain() {
        EnderecoResponse response = EnderecoMapper.mapEnderecoResponse(enderecoDomain);

        assertEquals(enderecoDomain.getUf(), response.getUf());
        assertEquals(enderecoDomain.getCep(), response.getCep());
        assertEquals(enderecoDomain.getBairro(), response.getBairro());
        assertEquals(enderecoDomain.getComplemento(), response.getComplemento());
        assertEquals(enderecoDomain.getLocalidade(), response.getLocalidade());
        assertEquals(enderecoDomain.getLogradouro(), response.getLogradouro());
    }

    @Test
    void shouldMapEnderecoResponseFromErrorResponse() {
        EnderecoResponse response = EnderecoMapper.mapEnderecoResponseError(errorResponse);

        assertNotNull(response.getErrorResponse());
        assertEquals(errorResponse.getCode(), response.getErrorResponse().getCode());
        assertEquals(errorResponse.getMessage(), response.getErrorResponse().getMessage());
    }
}