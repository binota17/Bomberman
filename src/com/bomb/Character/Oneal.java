package com.bomb.Character;

import com.bomb.Object.Object;
import com.bomb.GUI.Game;

import javax.swing.*;
import java.awt.*;

public class Oneal extends Enemy implements CanMove{
    private int dx = 0;
    private int dy = 0;
    private int movement = -2;
    int turn = 0;

    public Oneal(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/sprites/oneal_left1.png")).getImage();
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y,42, 42, null);
    }

    @Override
    public void move() {
        int tempX = Game.bomber.x - this.x;
        int tempY = Game.bomber.y - this.y;
        if(tempX < 0) dx = movement;
        else if(tempX > 0) dx = -movement;
        else dx = 0;
        if(tempY < 0) dy = movement;
        else if(tempY > 0) dy = -movement;
        else dy = 0;
        this.x += dx;
        if(this.x + dx < 0 || this.x + 60 + dx > 45*31 || collision()) this.x -=dx;
        this.y += dy;
        if(this.x + dx < 0 || this.x + 60 + dx > 45*31 || collision()) this.y -=dy;

    }

    private boolean collision(){
        for(Object object : Game.listObject){
            if(getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }
    public Rectangle getBound() {
        return new Rectangle(x, y, 42, 42);
    }

    public void changeDirection(){
        if(dx == 0 && dy > 0) this.image = new ImageIcon(getClass().getResource("/sprites/oneal_down1.png")).getImage();
        if(dx == 0 && dy < 0) this.image = new ImageIcon(getClass().getResource("/sprites/oneal_up1.png")).getImage();
        if(dx > 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/sprites/oneal_right1.png")).getImage();
        if(dx < 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/sprites/oneal_left2.png")).getImage();
    }

    protected int calculateColDirection() {
        if(Game.bomber.x < this.x)
            return 3;
        else if(Game.bomber.x> this.y)
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(Game.bomber.y< this.y)
            return 0;
        else if(Game.bomber.x > this.y)
            return 2;
        return -1;
    }
}
