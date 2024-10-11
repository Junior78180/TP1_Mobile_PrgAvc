package org.example;

import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
	super("le Mobile");
    Container leConteneur = getContentPane();
    sonMobile = new UnMobile(LARG, HAUT);
    leConteneur.add(sonMobile);

    JButton toggleButton = new JButton("Pause");
    toggleButton.addActionListener(e -> {
        if (sonMobile.isPaused) {
            sonMobile.resume();
            toggleButton.setText("Pause");
        } else {
            sonMobile.pause();
            toggleButton.setText("Resume");
        }
    });

    leConteneur.add(toggleButton, BorderLayout.SOUTH);

    Thread laTache = new Thread(sonMobile);
    laTache.start();
    setSize(LARG+50, HAUT);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
