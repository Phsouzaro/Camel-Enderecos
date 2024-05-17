package com.camel.demo.camel.processor;

import com.camel.demo.controller.response.ErrorResponse;
import com.camel.demo.mapper.EnderecoMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class HandleErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        var httpOperationFailedException = exchange.getException(HttpOperationFailedException.class);
        ErrorResponse errorResponse = new ErrorResponse();

        if (Objects.nonNull(httpOperationFailedException)) {
            errorResponse.setCode(httpOperationFailedException.getStatusCode());
            errorResponse.setMessage(httpOperationFailedException.getMessage());

        } else {
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage("Erro de Negócio");
        }

        exchange.getIn().setBody(EnderecoMapper.mapEnderecoResponseError(errorResponse));
    }
}
