package com.camel.demo.camel.processor;

import com.camel.demo.controller.request.EnderecoRequest;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscarEnderecoProcessorTest {

    private BuscarEnderecoProcessor buscarEnderecoProcessor;
    private Exchange exchange;

    @BeforeEach
    void setUp() {
        buscarEnderecoProcessor = new BuscarEnderecoProcessor();
        exchange = new DefaultExchange(new DefaultCamelContext());
    }

    @Test
    void shouldSetCepPropertyWhenCepIsPresentInRequestBody() throws Exception {
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep("12345");
        exchange.getIn().setBody(enderecoRequest);

        buscarEnderecoProcessor.process(exchange);

        assertEquals("12345", exchange.getProperty("REQUESTED_CEP"));
    }

    @Test
    void shouldNotSetCepPropertyWhenCepIsAbsentInRequestBody() throws Exception {
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        exchange.getIn().setBody(enderecoRequest);

        buscarEnderecoProcessor.process(exchange);

        assertEquals(null, exchange.getProperty("REQUESTED_CEP"));
    }
}