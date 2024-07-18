package com.bomb.Status;

import java.awt.*;
import com.bomb.GUI.Game;

public class Score {
    private Game game;
    private Font arial_20 = new Font("Arial", Font.BOLD, 20);

    public Score(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g) {
        g.setFont(arial_20);
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + game.score, 30,40 );
    }
}
