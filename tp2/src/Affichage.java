package tp2;
/**
 * 
 */
import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;


public class Affichage extends Thread{
	String texte;
    //on utilise static pour que tous les threads est le même semaphore
    static semaphore semaphoreBinaire = new semaphoreBinaire(1);

	public Affichage (String txt){texte=txt;}
	public void run(){
        semaphoreBinaire.syncWait();
        //la boucle for est la section critique
        for (int i=0; i<texte.length(); i++){
            //out est la ressource critique
            System.out.print(texte.charAt(i));
            try {sleep(100);} catch(InterruptedException e){};
        }
        semaphoreBinaire.syncSignal();
    }
}
