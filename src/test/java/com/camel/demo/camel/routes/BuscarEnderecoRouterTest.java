package com.camel.demo.camel.routes;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BuscarEnderecoRouterTest {

    @InjectMocks
    private BuscarEnderecoRouter buscarEnderecoRouter;

    @Test
    public void test() throws Exception {
        buscarEnderecoRouter.configure();
    }


}
