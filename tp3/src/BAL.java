package tp3;

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
    private String buffer;
    private Boolean available;

    public BAL (){
        this.buffer = null;
        this.available = false;
    }

    synchronized void deposer (String lettre) {
        while (available) {
            System.out.println("Producteur attend...");
            try {
                wait();
            }
            catch (InterruptedException e) {};
        }

        buffer = lettre;
        available = true;
        System.out.println("Producteur dépose : " + lettre);
        notify();
    }

    synchronized String retirer () {

        while (!available) {
            System.out.println("Consommeteur attend...");
            try {
                wait();
            }
            catch (InterruptedException e) {};
        }
        String lettre = buffer;
        available = false;
        System.out.println("Consommateur retire : " + lettre);

        notify();

        return lettre;
    }
}
