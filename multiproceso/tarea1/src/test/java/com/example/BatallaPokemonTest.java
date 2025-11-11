package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class BatallaPokemonTest {

    @Test
    public void testBatallaPokemon() throws InterruptedException{

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BatallaPokemon juego = new BatallaPokemon();
        Thread t1 = new Thread(juego.new HiloCharmander());
        Thread t2 = new Thread(juego.new HiloPikachu());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        String output = outContent.toString();
        assertTrue(output.contains("ha ganado la batalla!"));
        assertTrue(juego.juegoTerminado.get());
        assertTrue(juego.hpPikachu.get() <= 0 || juego.hpCharmander.get() <= 0);
    }

}
