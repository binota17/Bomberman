package com.bomb.Status;

import com.bomb.GUI.Game;

import java.awt.*;

public class Time {
    private Game game;
    private Font arial_20 = new Font("Arial", Font.BOLD, 20);

    public Time(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g) {
        g.setFont(arial_20);
        g.setColor(Color.WHITE);
        g.drawString("TIME: " + game.time, 600, 40);
    }
}
