package com.bomb.Character;

import com.bomb.Object.Object;
import com.bomb.GUI.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Ballom extends Enemy implements CanMove {

    private int dx = 1;
    private int dy = 0;
    int turn = 0;

    public Ballom(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/balloom_left1.png"))).getImage();
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y,45, 45, null);
    }

    @Override
    public void move() {
        if(isCrossWay() && this.x % 45  == 0 || isCrossWay() && this.y % 45 == 0){
            turn++;
            if(turn % 2 == 0){
                {
                    random();
                    this.x += dx;
                    this.y += dy;
                    if(this.x + dx < 0 || this.x + 40 + dx > 45*31 || collision()){
                        this.x -= dx;
                        this.y -= dy;
                        random();
                    }
                }
            }
        }
        else {
            this.x += dx;
            this.y += dy;
            if(this.x + dx < 0 || this.x + 40 + dx > 45*31 || collision()){
                this.x -= dx;
                this.y -= dy;
                random();
            }
        }
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
        return new Rectangle(x, y, 45, 45);
    }

    private boolean isCrossWay(){
        if(dx == 0){
            this.x+=1;
            if(!collision()){
                this.x-=1;
                return true;
            }
            this.x-=2;
            if(!collision()){
                this.x+=1;
                return true;
            }
            this.x+=1;
        }
        else if(dy==0){
            this.y+=1;
            if(!collision()){
                this.y-=1;
                return true;
            }
            this.y-=2;
            if(!collision()){
                this.y+=1;
                return true;
            }
            this.y+=1;
        }
        return false;
    }
    private void random(){
        Random random = new Random();
        int randomInt = random.nextInt(4) + 1;
        if(randomInt == 1) {
            dx = 0;
            dy = -1;
        }
        if(randomInt == 2){
            dx = 0;
            dy = 1;
        }
        if(randomInt == 3){
            dx = -1;
            dy = 0;
        }
        if(randomInt == 4){
            dx = 1;
            dy = 0;
        }
    }

    public void changeDirection() {
        if(dx == 0 && dy > 0) this.image = new ImageIcon(getClass().getResource("/sprites/balloom_left1.png")).getImage();
        if(dx == 0 && dy < 0) this.image = new ImageIcon(getClass().getResource("/sprites/balloom_right1.png")).getImage();
        if(dx > 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/sprites/balloom_up1.png")).getImage();
        if(dx < 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/sprites/balloom_down1.png")).getImage();
    }
}
