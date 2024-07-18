package com.bomb.Object;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Object {
    public int x, y;
    protected BufferedImage image;

    public abstract void drawObject(Graphics2D g2);

    public Rectangle getBound() {
        return new Rectangle(x, y, 45, 45);
    }
}
