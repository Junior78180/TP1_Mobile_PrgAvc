package org.example;

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 20, sonTemps = 100, sonCote = 40;
    boolean isPaused = false;
    static semaphoreBinaire sem = new semaphoreBinaire(1);

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        while (true) {
//            if (!isPaused) {
            for (sonDebDessin = 0; sonDebDessin < saLargeur / 3; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncWait();
            for (sonDebDessin = sonDebDessin; sonDebDessin < (saLargeur / 3) * 2; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncSignal();
            for (sonDebDessin = sonDebDessin; sonDebDessin < saLargeur; sonDebDessin += sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncWait();

            for (sonDebDessin = saLargeur  - sonPas; sonDebDessin >=( saLargeur / 3) * 2; sonDebDessin -= saLargeur / 3 + sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncSignal();
            for (sonDebDessin = saLargeur - sonPas; sonDebDessin >= saLargeur / 3; sonDebDessin -= saLargeur / 3 + sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncWait();
            for (sonDebDessin = saLargeur - sonPas; sonDebDessin >= 0; sonDebDessin -= saLargeur/3 + sonPas) {
                repaint();
                try {
                    Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) {
                    telleExcp.printStackTrace();
                }
            }
            sem.syncSignal();

//            } else {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException telleExcp) {
//                    telleExcp.printStackTrace();
//                }
//            }
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