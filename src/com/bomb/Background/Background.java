package com.bomb.Background;

import javax.swing.*;
import java.awt.*;

public class Background {
    public final Image image = new ImageIcon(getClass().getResource("/sprites/grass.png")).getImage();

    public void drawBackGround(Graphics2D g2) {
        g2.drawImage(image,45, 45,45*31 ,45*14 , null);
    }
}
