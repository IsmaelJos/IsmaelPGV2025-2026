package org.formacion.procesos.controllers;

import java.util.Scanner;

import org.formacion.procesos.services.ComandoLsService;
import org.formacion.procesos.services.ComandoPsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunnerController {

    @Autowired
    ComandoPsService ComandoControllerPs;
    @Autowired
    ComandoLsService ComandoControllerLs;

    public void menuConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos (CLI) Linux ===\n" +
                "Comandos:\n" +
                "  lsof -i\n" +
                "  top\n" +
                "  ps aux | head \n" );
          String linea = scanner.nextLine();  

          if (linea.toUpperCase().startsWith("PS")) {
            ComandoControllerPs.procesarLinea(linea);
          }else{
            ComandoControllerLs.procesarLinea(linea);
          }
          scanner.close();
    }

}
