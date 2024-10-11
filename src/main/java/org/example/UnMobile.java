package org.example;

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 40;
    boolean isPaused = false;

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        while (true) {
            if (!isPaused) {
                for (sonDebDessin = 0; sonDebDessin < saLargeur - sonPas; sonDebDessin += sonPas) {
                    repaint();
                    try {
                        Thread.sleep(sonTemps);
                    } catch (InterruptedException telleExcp) {
                        telleExcp.printStackTrace();
                    }
                }
                for (sonDebDessin = saLargeur - sonPas; sonDebDessin > 0; sonDebDessin -= sonPas) {
                    repaint();
                    try {
                        Thread.sleep(sonTemps);
                    } catch (InterruptedException telleExcp) {
                        telleExcp.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(100);
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