package org.example;

public class Affichage extends Thread {
    String texte;

    static SemaphoreBinaire sem = new SemaphoreBinaire(1);

    public Affichage(String txt) {
        texte = txt;
    }

    public void run() {
        sem.syncWait();
        //section critique
        System.out.println("J'entre en section critique");
        for (int i = 0; i < texte.length(); i++) {
            System.out.print(texte.charAt(i));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\nJe sors de section critique");
        sem.syncSignal();

    }
}
