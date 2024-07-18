
package com.bomb.Item;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class FlameItem extends Item {
    private Image img = new ImageIcon(getClass().getResource("/sprites/powerup_flames.png")).getImage();


    public FlameItem(int x, int y){
        this.x=x;
        this.y=y;
        try {
            this.image= ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_flames.png"));
        } catch (IOException ex) {
            Logger.getLogger(FlameItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void drawItem(Graphics2D g2) {
        g2.drawImage(img, this.x, this.y,45 ,45, null);
    }
}
