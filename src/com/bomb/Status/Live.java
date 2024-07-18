package com.bomb.Status;

import com.bomb.GUI.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Live {
    private Game game;
    private Font arial_20 = new Font("Arial", Font.BOLD, 20);
    private Image liveImage = new ImageIcon(getClass().getResource("/sprites/liveIcon.png")).getImage();


    public Live(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g) {
        g.setFont(arial_20);
        g.setColor(Color.WHITE);
        g.drawImage(liveImage, 270, 20, 27, 27, null );
        g.drawString("x " + game.getLive(), 300,40 );
    }
}
