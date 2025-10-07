package org.formacion.procesos;

import org.springframework.stereotype.Component;


@Component
public class Procesos {

    public void ejecutar() {
        System.out.println("Ejecutando logica del proceso...");
    }

}