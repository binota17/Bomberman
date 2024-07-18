package com.bomb.GUI;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {

    public static final int WIDTHJF = 910;
    public static final int HEIGHTJF = 665;
    private Container container;

    public GUI() {
        setSize(WIDTHJF, HEIGHTJF);
        setTitle("Bomber bitch");
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        container = new Container(this);
        add(container);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
        
    }
}
