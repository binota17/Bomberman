/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bomb.Item;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class MaxBoomb extends Item {
    public MaxBoomb(int x,int y){
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_bombpass.png"));
        } catch (IOException ex) {
            Logger.getLogger(MaxBoomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void drawItem(Graphics2D g2) {
        g2.drawImage(image, this.x, this.y,45 ,45, null);
    }
    
}
