package com.bomb.GUI;

import com.bomb.Character.Bomber;

public class Camera {
    private int x, y;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveCamera(Bomber bomber){
        if(bomber.getX() > Game.D_W/2 && bomber.getX() + Game.D_W/2 - Bomber.movement <45*29) this.x = -bomber.getX() + Game.D_W/2;
        else if(bomber.getX() <= Game.D_W/2)    this.x = 0;
    }
}
