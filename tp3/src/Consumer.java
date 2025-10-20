package tp3;

public class Consumer extends Thread {
    private BAL bal;

    public Consumer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // toutes les secondes, le consommateur retire une lettre
                Thread.sleep(1000);

                String lettre = bal.retirer(); // méthode bloquante (take)
                System.out.println("[" + Thread.currentThread().getName() + "] a retiré " + lettre);
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête.");
        }
    }
}
