
package com.bomb.Object;

import com.bomb.Item.Item;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Brick extends Object {

    public Brick(int x,int y)  {
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/sprites/brick.png"));
        } catch (IOException ex) {
            Logger.getLogger(Brick.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public Rectangle getBound() {
        return super.getBound();
    }

    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,45 ,45, null);
    }

    public boolean isItemInside(Item i){
        Rectangle rec=this.getBound();
        return rec.intersects(i.getBound());
    }
    
    
}
