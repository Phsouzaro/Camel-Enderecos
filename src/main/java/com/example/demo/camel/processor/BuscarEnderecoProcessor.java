package com.example.demo.camel.processor;

import com.example.demo.controller.request.EnderecoRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BuscarEnderecoProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.setProperty("REQUESTED_CEP", exchange.getIn().getBody(EnderecoRequest.class).getCep());
    }
}
