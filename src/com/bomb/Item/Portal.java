
package com.bomb.Item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Portal {
    int x,y;
    BufferedImage image;
    public Portal(int x,int y){
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/sprites/portal.png"));
        } catch (IOException ex) {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void drawPortal(Graphics g){
        g.drawImage(image, x, y, 45, 45,null);
    }
    public Rectangle getBound(){
        return new Rectangle(x, y, 45   ,45);
    }
}
