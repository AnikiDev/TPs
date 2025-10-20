package tp3;

public class Producer extends Thread {
    private BAL bal;
    private static int compteur = 0; // pour nommer les lettres produites

    public Producer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // toutes les secondes, un producteur dépose une lettre
                Thread.sleep(1000);

                String lettre = "Lettre-" + (++compteur);
                bal.deposer(lettre); // méthode bloquante (put)

                System.out.println("[" + Thread.currentThread().getName() + "] a déposé " + lettre);
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête.");
        }
    }
}


