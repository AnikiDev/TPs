package tp3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
On crée une classe BAL abstraction de la boite au lettre
Cette classe a 2 champs :
    - buffer abstraction d'une lettre
    - available abstraction de l'état de l'accès à buffer (0 pas accessible, 1 accessible)
Cette classe à au moins 2 méthodes
    - write qui permet d'écrire dans buffer (write est en exclusion mutuelle)
    - read qu permet de lire dans buffer
BAL EST UN MONITEUR (mutex)
 */
public class BAL {
    private BlockingQueue<String> queue;

    public BAL() {
        queue = new ArrayBlockingQueue<>(20);
    }

    public void deposer(String lettre) throws InterruptedException {
        System.out.println("Producteur dépose : " + lettre);
        queue.put(lettre); // bloquant si plein
    }

    public String retirer() throws InterruptedException {
        String lettre = queue.take(); // bloquant si vide
        System.out.println("Consommateur retire : " + lettre);
        return lettre;
    }

    public int getTaille() {
        return queue.size();
    }
}
