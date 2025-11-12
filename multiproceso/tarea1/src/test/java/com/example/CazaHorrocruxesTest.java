package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class CazaHorrocruxesTest {

    @Test
    public void testCazaHorrocruxes() throws InterruptedException{
    
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        Thread t1 = new Thread(CazaHorrocruxes.Buscador("Harry", "Bosque Prohibido"));
        Thread t2 = new Thread(CazaHorrocruxes.Buscador("Hermione", "Biblioteca Antigua"));
        Thread t3 = new Thread(CazaHorrocruxes.Buscador("Ron", "Mazmorras del castillo"));

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
