package org.example;

public class Producer extends Thread {

    private final BoiteALettre boiteALettre;

    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ*".toCharArray();


    public Producer(BoiteALettre bal) {
        boiteALettre = bal;

    }

    public void run() {

        try {
            for (char lettre : alphabet) {
                Thread.sleep(200);


                while (true) {
                    if (!boiteALettre.deposer(lettre)) {
                        System.out.println("Echec de dépôt de la lettre : " + lettre + " | la boite est pleine " + boiteALettre.getQueueSize());
                    } else {
                        System.out.println("J'ai déposé la lettre : " + lettre + " | Nombre dans la file : " + boiteALettre.getQueueSize());
                        break;
                    }
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Interruption lors du dépôt de la lettre.");
        }
    }

}