package org.formacion.procesos.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComandoPsServiceTest {
    ComandoPsService comandoPsService;
    
    @BeforeEach
    void before(){
        comandoPsService = new ComandoPsService();
        comandoPsService.setComando("ps");
    }

    @Test
    void validarLaTest(){
        String[] arrayComando = {"ps","-xa"};
        boolean valida = comandoPsService.validar(arrayComando);
        Assertions.assertTrue(valida,"se ha producido un error en la validacion");
    }

    @Test
    void validarFalseTest(){
        String[] arrayComando = {"ps","lalala"};
        boolean valida = comandoPsService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }
    @Test
    void validarFalse2Test(){
        String[] arrayComando = {"ps","-j"};
        boolean valida = comandoPsService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }
}
