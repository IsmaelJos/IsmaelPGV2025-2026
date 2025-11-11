package test.java.com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.java.com.example.ExploradoresJedi;

public class ExploradoresJediTest {
    @Test
    void ExploradoresJediUnSoloGanador() {
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        ExploradoresJedi e = new ExploradoresJedi();
        e.main();

        String salida = salidaCapturada.toString();

        assertTrue(e.isPistaEncontrada(), "Debe haberse encontrado una pista");
        long ocurrencias = salida.split("halló una pista", -1).length - 1;
        assertEquals(1, ocurrencias, "Debe haber exactamente un hallazgo en la salida");
    }
}
