package org.formacion.procesos.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComandoLsServiceTest {
    ComandoLsService comandoLsService;
    
    @BeforeEach
    void before(){
        comandoLsService = new ComandoLsService();
        comandoLsService.setComando("ls");
    }

    @Test
    void validarLaTest(){
        String[] arrayComando = {"ls","-la"};
        boolean valida = comandoLsService.validar(arrayComando);
        Assertions.assertTrue(valida,"se ha producido un error en la validacion");
    }

    @Test
    void validarFalseTest(){
        String[] arrayComando = {"ls","lalala"};
        boolean valida = comandoLsService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }
    @Test
    void validarFalse2Test(){
        String[] arrayComando = {"ls","-j"};
        boolean valida = comandoLsService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }
}
