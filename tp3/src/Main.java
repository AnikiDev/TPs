package tp3;

public class Main {
    public static void main(String[] args) {
        // Création de la BAL partagée
        BAL bal = new BAL();

        // Création des producteurs
        Producer producer1 = new Producer(bal);
        Producer producer2 = new Producer(bal);

        // Création des consommateurs
        Consumer consumer1 = new Consumer(bal);
        Consumer consumer2 = new Consumer(bal);

        // Démarrage des threads
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Laisser tourner le système pendant 10 secondes
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Arrêt propre des threads
        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulation terminée.");
    }
}
