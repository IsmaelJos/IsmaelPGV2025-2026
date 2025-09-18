package concurencia;

public class ConcurrenciaVsParalelismo {
    public static void main(String[] args) throws InterruptedException {
        Runnable cocinar = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Cocinando paso " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        };

        Runnable lavar = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lavando paso " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        };

        System.out.println("=== SECUENCIAL ===");
        cocinar.run();
        lavar.run();

        System.out.println("=== CONCURRENTE/PARALELO ===");
        Thread t1 = new Thread(cocinar);
        Thread t2 = new Thread(lavar);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
