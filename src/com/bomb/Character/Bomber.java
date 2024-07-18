package com.bomb.Character;

import GameSound.GameSound;
import com.bomb.Object.Bomb;
import com.bomb.Object.Object;
import com.bomb.GUI.Game;
import com.bomb.Item.MaxBoomb;
import com.bomb.Item.BombItem;
import com.bomb.Item.Portal;
import com.bomb.Item.FlameItem;
import com.bomb.Item.Item;
import com.bomb.Item.SpeedItem;

import javax.swing.*;
import java.awt.*;

public class Bomber extends Character implements CanMove {
    public boolean restart = false;
    public int frameDead = 1;
    public boolean isAlive = true;
    static public int movement = 6;
    public int bombSize = 1;
    public int dx = 0;
    public int dy = 0;
    public static int direction = 1;
    public int currentBomb = 0;
    public int maxBomb = 1;

    private Image imageUp1 = new ImageIcon(getClass().getResource("/sprites/player_up_1.png")).getImage();
    private Image imageUp2 = new ImageIcon(getClass().getResource("/sprites/player_up_2.png")).getImage();
    private Image imageDown1 = new ImageIcon(getClass().getResource("/sprites/player_down_1.png")).getImage();
    private Image imageDown2 = new ImageIcon(getClass().getResource("/sprites/player_down_2.png")).getImage();
    private Image imageLeft1 = new ImageIcon(getClass().getResource("/sprites/player_left_1.png")).getImage();
    private Image imageLeft2 = new ImageIcon(getClass().getResource("/sprites/player_left_2.png")).getImage();
    private Image imageRight1 = new ImageIcon(getClass().getResource("/sprites/player_right_1.png")).getImage();
    private Image imageRight2 = new ImageIcon(getClass().getResource("/sprites/player_right_2.png")).getImage();
    private Image imageDead1 = new ImageIcon(getClass().getResource("/sprites/player_dead.png")).getImage();
    private Image imageDead2 = new ImageIcon(getClass().getResource("/sprites/player_dead_1.png")).getImage();
    private Image imageDead3 = new ImageIcon(getClass().getResource("/sprites/player_dead_2.png")).getImage();

    private Image[] imageUp = {imageUp2, imageUp1};
    private Image[] imageDown = {imageDown2, imageDown1};
    private Image[] imageLeft = {imageLeft2, imageLeft1};
    private Image[] imageRight = {imageRight2, imageRight1};


    @Override
    public void move() {
        if(isAlive){
            this.x += dx;
            if (this.x + dx < 0 || this.x + 10 + dx > 45 * 31 || collision()) {
                this.x -= dx;
            }
            this.y += dy;
            if (this.y + dy < 0 || this.y + 10 + dy > Game.D_H || collision()) {
                this.y -= dy;
            }
        }
    }

    public Bomber(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/sprites/player_down.png")).getImage();
    }

    public void changeDirection(int direction) {
        switch (direction) {
            case UP:
                this.direction = Character.UP;
                image = new ImageIcon(getClass().getResource("/sprites/player_up.png")).getImage();
                break;
            case DOWN:
                this.direction = Character.DOWN;
                image = new ImageIcon(getClass().getResource("/sprites/player_down.png")).getImage();
                break;
            case LEFT:
                this.direction = Character.LEFT;
                image = new ImageIcon(getClass().getResource("/sprites/player_left.png")).getImage();
                break;
            case RIGHT:
                this.direction = Character.RIGHT;
                image = new ImageIcon(getClass().getResource("/sprites/player_right.png")).getImage();
                break;
        }
    }

    @Override
    public void drawCharacter(Graphics2D g2) {
        if(isAlive){
            if (dx == 0 && !Game.isKeyPressed || dy == 0 && !Game.isKeyPressed) {
                g2.drawImage(image, x, y, 40, 40, null);
            } else {
                if (direction == 1) {
                    if (Game.framesUp % 2 == 0) {
                        g2.drawImage(imageUp[0], x, y, 40, 40, null);
                    }
                    if (Game.framesUp % 2 == 1) {
                        g2.drawImage(imageUp[1], x, y, 40, 40, null);
                    }

                }
                if (direction == 2) {
                    if (Game.framesDown % 2 == 0) {
                        g2.drawImage(imageDown[0], x, y, 40, 40, null);

                    }
                    if (Game.framesDown % 2 == 1) {
                        g2.drawImage(imageDown[1], x, y, 40, 40, null);

                    }

                }
                if (direction == 3) {
                    if (Game.framesLeft % 2 == 0) {
                        g2.drawImage(imageLeft[0], x, y, 40, 40, null);

                    }
                    if (Game.framesLeft % 2 == 1) {
                        g2.drawImage(imageLeft[1], x, y, 40, 40, null);

                    }

                }
                if (direction == 4) {
                    if (Game.framesRight % 2 == 0) {
                        g2.drawImage(imageRight[0], x, y, 40, 40, null);

                    }
                    if (Game.framesRight % 2 == 1) {
                        g2.drawImage(imageRight[1], x, y, 40, 40, null);

                    }

                }
            }
        } else{
            if (frameDead % 3 == 0) g2.drawImage(imageDead1, this.x, this.y, 40, 40 , null);

            if (frameDead % 3 == 1) g2.drawImage(imageDead2, this.x, this.y, 40, 40 , null);
            if (frameDead % 3 == 2) {
                g2.drawImage(imageDead3, this.x, this.y, 40, 40 , null);
                isAlive = true;
                restart = true;
                frameDead++;
            }
        }
    }

    public boolean impactWithMonster(Enemy monster){
        Rectangle rec=monster.getBound();
        return (rec.intersects(getBound()));
    }
    public boolean insertItem(Item it) {
        Rectangle rec1 =it.getBound();
        if (rec1.intersects(getBound())) {
            GameSound gameSound = new GameSound("./src/GameSound/item.wav");
            gameSound.play();
            if(it instanceof FlameItem) bombSize++;
            if(it instanceof SpeedItem) movement++;
            if(it instanceof BombItem) maxBomb++;
            if(it instanceof MaxBoomb) bombSize=9;
            return true;
        }
        return false;
    }
    
    public boolean isInsertPortal(Portal p){
        Rectangle rec=p.getBound();
        if(rec.intersects(getBound())){
            return true;
        }
        return false;
    }
    @Override
    public int getX() {
        return super.getX();
    }

    private boolean collision() {
        for (Object object : Game.listObject) {
            if (object instanceof Bomb) {
                if (!getBound().intersects(object.getBound())) {
                    ((Bomb) object).c = 1;
                }
                if (((Bomb) object).c == 0) {
                    return false;
                }
            }
            if (getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, 38, 38);
    }

    public int getHeight() {
        return 40;
    }

    public int getWidth() {
        return 40;
    }
}
