/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bomb.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author thota
 */
public abstract class Item {

    public int x, y;
    BufferedImage image;
    public int frames = 0;

    public abstract void drawItem(Graphics2D g2);

    public Rectangle getBound(){
        return new Rectangle(x, y, image.getWidth(),image.getHeight());
    }
}
