package com.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BatallaPokemon{

    volatile AtomicBoolean juegoTerminado = new AtomicBoolean(false);
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
        if (hpObjetivo.get() <= 0 && juegoTerminado.get() == false) {
            juegoTerminado = new AtomicBoolean(true);
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
            while (!juegoTerminado.get()) {
                m.lock();
                while (turno != "Pikachu" && !juegoTerminado.get()) {
                    try {
                        turnoCambio.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (juegoTerminado.get()) {
                    m.unlock();
                }else{
                    atacar("Pikachu", hpCharmander);
                    turno = "Charmander";
                    turnoCambio.signal();
                    m.unlock();
                }
                
            }
        }
    }
    
    public class HiloCharmander implements Runnable {
        @Override
        public void run(){
            while (!juegoTerminado.get()) {
                m.lock();
                while (turno != "Charmander" && !juegoTerminado.get()) {
                    try {
                        turnoCambio.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (juegoTerminado.get()) {
                    m.unlock();
                }else{
                    atacar("Charmander", hpPikachu);
                    turno = "Pikachu";
                    turnoCambio.signal();
                    m.unlock();
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {

        BatallaPokemon batallaPokemon = new BatallaPokemon();
        // Thread t1 = new Thread(batallaPokemon.new HiloPokemon("Pikachu"));
        // Thread t2 = new Thread(batallaPokemon.new HiloPokemon("Charmander"));
         Thread t1 = new Thread(batallaPokemon.new HiloCharmander());
         Thread t2 = new Thread(batallaPokemon.new HiloPikachu());
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}
