package com.camel.demo.controller;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private ProducerTemplate producerTemplate;

    @Test
    public void testBuscaEnderecoPorCep() {
        Mockito.when(producerTemplate.requestBody(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(null);

        enderecoController.buscaEnderecoPorCep(null);

        Mockito.verify(producerTemplate, Mockito.times(1)).requestBody(Mockito.anyString(), Mockito.any(), Mockito.any());
    }

}
