package com.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BatallaPokemon{

    private volatile boolean juegoTerminado = false;
    AtomicInteger hpPikachu = new AtomicInteger(100);
    AtomicInteger hpCharmander = new AtomicInteger(100);
    String turno = "Pikachu";  // alternancia estricta
    ReentrantLock m = new ReentrantLock();
    Condition turnoCambio = m.newCondition();

    public void atacar(String atacante, AtomicInteger hpObjetivo) {
        Random random = new Random();
        int dano = random.nextInt(15)+5;
        hpObjetivo.addAndGet(-dano);
        System.out.println(atacante + " ataca con " + dano + " de daño. HP rival: " + hpObjetivo);
        if (hpObjetivo.get() <= 0 && juegoTerminado == false) {
            juegoTerminado = true;
            System.out.println(atacante + " ha ganado la batalla!");
        }
        try {
            Thread.sleep(random.nextInt(400)+200);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public class HiloPikachu implements Runnable {
        @Override
        public void run(){
            while (!juegoTerminado) {
                m.lock();
                while (turno != "Pikachu" && !juegoTerminado) {
                    try {
                        turnoCambio.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (juegoTerminado) {
                    m.unlock();
                }
                atacar("Pikachu", hpCharmander);
                turno = "Charmander";
                turnoCambio.signal();
                m.unlock();
            }
        }
    }
    public class HiloCharmander implements Runnable {
        @Override
        public void run(){
            while (!juegoTerminado) {
                m.lock();
                while (turno != "Charmander" && !juegoTerminado) {
                    try {
                        turnoCambio.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (juegoTerminado) {
                    m.unlock();
                }
                atacar("Charmander", hpPikachu);
                turno = "Pikachu";
                turnoCambio.signal();
                m.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        BatallaPokemon batallaPokemon = new BatallaPokemon();
        Thread t1 = new Thread(batallaPokemon.new HiloPikachu());
        Thread t2 = new Thread(batallaPokemon.new HiloCharmander());
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}
