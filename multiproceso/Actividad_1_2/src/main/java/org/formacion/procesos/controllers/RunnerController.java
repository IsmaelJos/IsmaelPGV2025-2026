package org.formacion.procesos.controllers;

import java.util.Scanner;

import org.formacion.procesos.services.ComandoLsofService;
import org.formacion.procesos.services.ComandoPsService;
import org.formacion.procesos.services.ComandoTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunnerController {

    @Autowired
    ComandoPsService ComandoControllerPs;
    @Autowired
    ComandoTopService ComandoControllerTop;
    @Autowired
    ComandoLsofService ComandoControllerLsof;

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
          }else if(linea.toUpperCase().startsWith("LSOF")){
            ComandoControllerLsof.procesarLinea(linea);
          }else{
            ComandoControllerTop.procesarLinea(linea);
          }
          scanner.close();
    }

}
