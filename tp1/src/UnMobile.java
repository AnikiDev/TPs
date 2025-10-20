import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 40;
    Color saCouleur = Color.BLACK;

    // Trois verrous pour les trois zones de la fenêtre
    static final Object[] zones = { new Object(), new Object(), new Object() };
    // Indique si la zone est occupée (true = occupée, false = libre)
    static final boolean[] zoneOccupee = { false, false, false };

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        int zone1Fin = saLargeur / 3;
        int zone2Fin = 2 * saLargeur / 3;
        boolean versDroite = true;

        while (true) {
            if (versDroite) {
                // Déplacement vers la droite

                // Zone 1
                entrerZone(0);
                for (; sonDebDessin < zone1Fin; sonDebDessin += sonPas) {
                    saCouleur = Color.BLACK;
                    repaint();
                    sleep();
                }
                sortirZone(0);

                // Zone 2 (zone centrale, mobile devient rouge)
                entrerZone(1);
                for (; sonDebDessin < zone2Fin; sonDebDessin += sonPas) {
                    saCouleur = Color.RED;
                    repaint();
                    sleep();
                }
                sortirZone(1);

                // Zone 3
                entrerZone(2);
                for (; sonDebDessin < saLargeur - sonCote; sonDebDessin += sonPas) {
                    saCouleur = Color.BLACK;
                    repaint();
                    sleep();
                }
                sortirZone(2);

                versDroite = false; // inversion du sens
            } else {
                // Déplacement vers la gauche

                // Zone 3
                entrerZone(2);
                for (; sonDebDessin > zone2Fin; sonDebDessin -= sonPas) {
                    saCouleur = Color.BLACK;
                    repaint();
                    sleep();
                }
                sortirZone(2);

                // Zone 2 (zone centrale, mobile devient rouge)
                entrerZone(1);
                for (; sonDebDessin > zone1Fin; sonDebDessin -= sonPas) {
                    saCouleur = Color.RED;
                    repaint();
                    sleep();
                }
                sortirZone(1);

                // Zone 1
                entrerZone(0);
                for (; sonDebDessin > 0; sonDebDessin -= sonPas) {
                    saCouleur = Color.BLACK;
                    repaint();
                    sleep();
                }
                sortirZone(0);

                versDroite = true; // inversion du sens
            }
        }
    }

    // Méthode pour entrer dans une zone : attend si la zone est occupée
    private void entrerZone(int zone) {
        synchronized (zones[zone]) {
            while (zoneOccupee[zone]) {
                try {
                    zones[zone].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            zoneOccupee[zone] = true;
        }
    }

    // Méthode pour sortir d'une zone : libère la zone et prévient les autres
    private void sortirZone(int zone) {
        synchronized (zones[zone]) {
            zoneOccupee[zone] = false;
            zones[zone].notifyAll();
        }
    }

    // Pause entre chaque déplacement
    private void sleep() {
        try {
            Thread.sleep(sonTemps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Dessin du mobile
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(saCouleur);
        g.fillRect(sonDebDessin, saHauteur / 2 - sonCote / 2, sonCote, sonCote);
    }
}
