package com.camel.demo.controller;

import com.camel.demo.camel.routes.BuscarEnderecoRouter;
import com.camel.demo.controller.request.EnderecoRequest;
import com.camel.demo.controller.response.EnderecoResponse;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/cep")
    public EnderecoResponse buscaEnderecoPorCep(@Valid @RequestBody EnderecoRequest request) {
        return producerTemplate.requestBody(BuscarEnderecoRouter.ROUTE_BUSCAR_ENDERECO_URI, request, EnderecoResponse.class);
    }
}
