import javax.swing.*;
import java.awt.*;

public class UneFenetre extends JFrame {
    private final int LARG = 600, HAUT = 300;  // largeur et hauteur de la fenêtre
    int qtMobile = 5;  // nombre de mobiles à afficher

    public UneFenetre() {
        setSize(LARG, HAUT);  // définition de la taille de la fenêtre

        Container leConteneur = getContentPane();  // récupération du conteneur principal
        leConteneur.setLayout(new GridLayout(qtMobile, 1));  // mise en place d'une grille qtMobile lignes, 1 colonne

        UnMobile[] sonMobileN = new UnMobile[qtMobile];  // tableau pour stocker les mobiles
        Thread[] laThread = new Thread[qtMobile];  // tableau pour stocker les threads des mobiles

        for (int i = 0; i < qtMobile; i++) {  
            sonMobileN[i] = new UnMobile(LARG, HAUT / qtMobile);  // création d'un mobile avec la largeur de la fenêtre et une hauteur proportionnelle
            leConteneur.add(sonMobileN[i]);  // ajout du mobile au conteneur
        }

        setVisible(true);  // affichage de la fenêtre après ajout des mobiles

        for (int i = 0; i < qtMobile; i++) {  
            laThread[i] = new Thread(sonMobileN[i]);  // création d'un thread pour chaque mobile
            laThread[i].start();  // démarrage du thread pour lancer l'animation
        }
    }
}
