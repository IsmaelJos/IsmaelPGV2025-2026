package com.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CazaHorrocruxes {

    private final static AtomicBoolean encontrado = new AtomicBoolean(false);
    private final static AtomicReference<String> ganador = new AtomicReference<>(null);

    public static Runnable Buscador(String nombre, String ubicacion){
        return ()->{
        Random random = new Random();
        int tiempo = random.nextInt(1500)+500;
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!encontrado.get()) {
                encontrado.set(true);
                ganador.set(nombre);
                System.out.println(nombre + " encontró un Horrocrux en " + ubicacion + ". ¡Búsqueda terminada!");
            }
        };
    }
    
    public static void main(String[] args) {

        Thread t1 = new Thread(Buscador("Harry", "Bosque Prohibido"));
        Thread t2 = new Thread(Buscador("Hermione", "Biblioteca Antigua"));
        Thread t3 = new Thread(Buscador("Ron", "Mazmorras del castillo"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
