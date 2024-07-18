package com.bomb.Character;

import java.awt.*;

public abstract class Character {
    public int x, y, speed;
    public static final int UP = 1;
    public static final int DOWN= 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public  Image image;

    public abstract void drawCharacter(Graphics2D g2);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
