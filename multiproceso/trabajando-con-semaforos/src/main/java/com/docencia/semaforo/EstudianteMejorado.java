package com.docencia.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class EstudianteMejorado implements Runnable{

    private String nombre;
    private static final Semaphore semaphore = new Semaphore(4);

    public EstudianteMejorado(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("El estudiante "+nombre+" ha comenzado a utilizar el equipo");
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 501));
            System.out.println("El estudiante "+nombre+" ha finalizado con el equipo"+semaphore.availablePermits()+1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            semaphore.release();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread goku = new Thread(new LaboratorioConstruccion("Goku"));
        Thread vegeta = new Thread(new LaboratorioConstruccion("vegeta"));
        Thread Brian = new Thread(new LaboratorioConstruccion("Brian"));
        Thread Roman = new Thread(new LaboratorioConstruccion("Roman"));
        Thread Daniel = new Thread(new LaboratorioConstruccion("Daniel"));
        Thread Ismael = new Thread(new LaboratorioConstruccion("Ismael"));

        goku.start();
        vegeta.start();
        Brian.start();
        Roman.start();
        Daniel.start();
        Ismael.start();

        goku.join();
        vegeta.join();
        Brian.join();
        Roman.join();
        Daniel.join();
        Ismael.join();
    }
}
