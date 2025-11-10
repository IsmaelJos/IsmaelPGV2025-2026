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
        juego.main(null);
        String output = outContent.toString();
        assertTrue(output.contains("ha ganado la batalla!"));
        assertTrue(juego.juegoTerminado.get());
        assertTrue(juego.hpPikachu.get() <= 0 || juego.hpCharmander.get() <= 0);
    }

}
