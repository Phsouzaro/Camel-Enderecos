package com.camel.demo.camel.routes;

import com.camel.demo.camel.processor.BuscarEnderecoProcessor;
import com.camel.demo.camel.processor.HandleErrorProcessor;
import com.camel.demo.domain.EnderecoDomain;
import com.camel.demo.mapper.EnderecoMapper;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class BuscarEnderecoRouter extends RouteBuilder {

    public static final String ROUTE_BUSCAR_ENDERECO_URI = "direct:buscarEnderecoRoute";

    public static final String ROUTE_ERRO = "direct:erro";

    @Override
    public void configure() throws Exception {

        onException(HttpOperationFailedException.class).to(ROUTE_ERRO);
        onException(Exception.class).to(ROUTE_ERRO);

        from(ROUTE_ERRO)
                .process(new HandleErrorProcessor());

        from(ROUTE_BUSCAR_ENDERECO_URI)
                .routeId("routeBuscarEnderecoId")
                .description("BUSCA ENDERECO NA API PUBLICA DO GOVERNO")
                .process(new BuscarEnderecoProcessor())
                .removeHeader(Exchange.HTTP_URI)
                .marshal().json(JsonLibrary.Jackson)
                .setHeader("Content-type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("https://viacep.com.br/ws/${exchangeProperty.REQUESTED_CEP}/json/")
                .unmarshal(new JacksonDataFormat(EnderecoDomain.class))
                .bean(EnderecoMapper.class, "mapEnderecoResponse(${body})")
                .end();
    }
}
