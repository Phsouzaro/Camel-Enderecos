package com.camel.demo.camel.processor;

import com.camel.demo.controller.response.EnderecoResponse;
import com.camel.demo.controller.response.ErrorResponse;
import org.apache.camel.Exchange;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandleErrorProcessorTest {

    private HandleErrorProcessor handleErrorProcessor;
    private Exchange exchange;

    @BeforeEach
    void setUp() {
        handleErrorProcessor = new HandleErrorProcessor();
        exchange = new DefaultExchange(new DefaultCamelContext());
    }

    @Test
    void shouldHandleHttpOperationFailedException() throws Exception {
        HttpOperationFailedException exception = new HttpOperationFailedException(null, 404, null, null, null, null);
        exchange.setException(exception);

        handleErrorProcessor.process(exchange);

        ErrorResponse response = exchange.getIn().getBody(EnderecoResponse.class).getErrorResponse();
        assertEquals(404, response.getCode());
        assertEquals(exception.getMessage(), response.getMessage());
    }

    @Test
    void shouldHandleGenericException() throws Exception {
        Exception exception = new Exception("Erro de Negócio");
        exchange.setException(exception);

        handleErrorProcessor.process(exchange);

        ErrorResponse response = exchange.getIn().getBody(EnderecoResponse.class).getErrorResponse();
        assertEquals(400, response.getCode());
        assertEquals("Erro de Negócio", response.getMessage());
    }

    @Test
    void shouldNotHandleExceptionWhenNoneIsPresent() throws Exception {
        handleErrorProcessor.process(exchange);

        ErrorResponse response = exchange.getIn().getBody(ErrorResponse.class);
        assertTrue(response == null);
    }
}