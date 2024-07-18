package com.bomb.Object;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomb extends Object {
    public int c;
    public int lifeTime;
    public int framesBomb = 0;
    private Image imageBomb1 = new ImageIcon(getClass().getResource("/sprites/bomb_0.png")).getImage();
    private Image imageBomb2 = new ImageIcon(getClass().getResource("/sprites/bomb_1.png")).getImage();
    public boolean impactRightBomb = false;
    public boolean impactLeftBomb = false;
    public boolean impactUpBomb = false;
    public boolean impactDownBomb = false;

    private Bomb(int x, int y){
        x=(x/45)*45;
        y=(y/45)*45;
        this.x = x;
        this.y = y;
        c=0;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_2.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bomb(int x, int y, int lifeTime){
        this(x, y);
        this.lifeTime = lifeTime;
    }

    public void explose(){
        lifeTime --;
    }

    public void instantExplose() {
        lifeTime = -1;
    }

    @Override
    public void drawObject(Graphics2D g2) {
        if(framesBomb % 3 == 0) g2.drawImage(this.image, this.x, this.y, 45, 45, null);
        if(framesBomb % 3 == 1) g2.drawImage(imageBomb1, this.x, this.y, 45, 45, null);
        if(framesBomb % 3 == 2) g2.drawImage(imageBomb2, this.x, this.y, 45, 45, null);
    }
    public Rectangle getBound() {
        return new Rectangle(x, y, 45, 45);
    }
}
