package org.example;


public class Consumer extends Thread {

    private final BoiteALettre boiteALettre;

    public Consumer(BoiteALettre bal) {
        boiteALettre = bal;
    }


    public void run() {

        try {
            while (true) {
                Thread.sleep(500);

                Character lettreRecupere = boiteALettre.retirer();

                if (lettreRecupere == null) {
                    System.out.println("Aucune lettre disponible pour récupération.");
                    continue;
                }

                if (lettreRecupere == '*') {
                    System.out.println("Fin du traitement, le consommateur arrête.");
                    break;
                }

                System.out.println("J'ai récupéré la lettre : " + lettreRecupere + " | Nombre dans la file : " + boiteALettre.getQueueSize());

            }
        } catch (InterruptedException e) {
            System.out.println("Interruption lors de la récupération de la lettre.");
        }
    }

}