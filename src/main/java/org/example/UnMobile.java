package org.example;

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 20, sonTemps = 50, sonCote = 10;
    boolean isPaused = false;
    static SemaphoreBinaire semaphoreBinaire = new SemaphoreBinaire(2);

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        while (true) {
//            if (!isPaused) {
            // Phase 1
            for (sonDebDessin = 0; sonDebDessin < saLargeur / 3; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            semaphoreBinaire.syncWait();

            // Phase 2
            for (sonDebDessin = saLargeur / 3; sonDebDessin < (saLargeur / 3) * 2; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            semaphoreBinaire.syncSignal();

            // Phase 3
            for (sonDebDessin = (saLargeur / 3) * 2; sonDebDessin < saLargeur; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }

            // Phase 4
            for (sonDebDessin = saLargeur - sonPas; sonDebDessin >= (saLargeur / 3) * 2; sonDebDessin -= sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }

            semaphoreBinaire.syncWait();
            // Phase 5
            for (sonDebDessin = (saLargeur / 3) * 2 - sonPas; sonDebDessin >= saLargeur / 3; sonDebDessin -= sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            semaphoreBinaire.syncSignal();
            // Phase 6
            for (sonDebDessin = saLargeur / 3 - sonPas; sonDebDessin >= 0; sonDebDessin -= sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
        }
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}