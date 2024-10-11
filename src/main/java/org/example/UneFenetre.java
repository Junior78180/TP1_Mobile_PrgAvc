package org.example;

import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=200, HAUT=100;
    private final int NBRLIG = 4, NBRCOL = 1;

    public UneFenetre()
    {
	super("le Mobile");
    Container leConteneur = getContentPane();
    leConteneur.setLayout(new GridLayout(NBRLIG, NBRCOL));

    for (int i = 0; i < NBRLIG; i++){
        for (int j = 0; j < NBRCOL; j++){
            UnMobile sonMobile = new UnMobile(LARG, HAUT);
            leConteneur.add(sonMobile);
            Thread laTache1 = new Thread(sonMobile);
            laTache1.start();
        }
    }

//    JButton button = new JButton("Stop");
//    button.addActionListener(e -> {
//        if (sonMobile.isPaused) {
//            sonMobile.resume();
//            button.setText("Pause");
//        } else {
//            sonMobile.pause();
//            button.setText("Resume");
//        }
//    });
//    leConteneur.add(button);

    setSize(LARG*NBRCOL+HAUT, HAUT*NBRCOL+LARG);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
