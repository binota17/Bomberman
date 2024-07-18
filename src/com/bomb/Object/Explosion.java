package com.bomb.Object;

import com.bomb.Character.Character;
import com.bomb.Character.Enemy;
import com.bomb.GUI.Game;
import com.bomb.Item.Item;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static com.bomb.GUI.Game.listEnemy;

public class Explosion extends Object {

    private BufferedImage img_left, img_right, img_up, img_down, img_center;
    private Image img_down_2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/explosion_vertical2.png"))).getImage();
    private Image img_up_2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/explosion_vertical2.png"))).getImage();
    private Image img_left_2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/explosion_horizontal2.png"))).getImage();
    private Image img_right_2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/explosion_horizontal2.png"))).getImage();
    private Image img_center_2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/bomb_exploded2.png"))).getImage();
    public int time;
    private int size;
    private int right = 0, left = 0, up = 0, down = 0;
    public boolean isRemove = false;
    private int tempRight, tempLeft, tempUp, tempDown;
    private boolean isDrawLeft;
    private boolean isDrawRight;
    private boolean isDrawUp;
    private boolean isDrawDown;
    private boolean endUp = false;
    private boolean endDown = false;
    private boolean endLeft = false;
    private boolean endRight = false;

    public Explosion(int x, int y, int time, int size, boolean isDrawRight, boolean isDrawLeft, boolean isDrawDown, boolean isDrawUp) {
        this.x = x;
        this.y = y;
        this.size = size;
        tempRight = size;
        tempUp = size;
        tempDown = size;
        tempLeft = size;
        this.isDrawRight = isDrawRight;
        this.isDrawLeft = isDrawLeft;
        this.isDrawUp = isDrawUp;
        this.isDrawDown = isDrawDown;
        this.time = time;
        try {
            img_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/explosion_horizontal_left_last2.png")));
            img_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/explosion_horizontal_right_last2.png")));
            img_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/explosion_vertical_top_last2.png")));
            img_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/explosion_vertical_down_last2.png")));
            img_center = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/bomb_exploded2.png")));
        } catch (IOException ex) {
            Logger.getLogger(Explosion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove() {
        time --;
    }

    private void removeBrick(ArrayList<Object> list, Brick brick) {//hàm xóa
        Iterator<Object> ite = list.iterator();//dùng Iterator để xóa, nếu dùng list.remove thì sẽ ko dồn lên mà phần tử đó sẽ trở thành null và lần duyệt tiếp theo sẽ sai
        while (ite.hasNext()) {
            Object object = ite.next();//ite.next sẽ trả về đối tượng tiếp theo của list
            if (object instanceof Brick) {
                if (object.equals(brick)) {
                    ite.remove();
                    return;
                }
            }
        }
    }

    private boolean Right0(ArrayList<Object> list, int x, int y, int size) {
        for (Object obj : list) {
            if (obj.y == y) {
                if (obj.x == x + 45 * size) {
                    if (obj instanceof Bomb) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactLeftBomb = true;
                    } else if (obj instanceof Brick && right == 0) {
                        removeBrick(Game.listObject, (Brick) obj);
                        right++;
                    }
                    tempRight = size;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Left0(ArrayList<Object> list, int x, int y, int size) {
        for (Object obj : list) {
            if (obj.y == y) {
                if (obj.x == x - 45 * size) {
                    if (obj instanceof Bomb) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactRightBomb = true;
                        endLeft = true;
                    } else if (obj instanceof Brick && left == 0) {
                        removeBrick(Game.listObject, (Brick) obj);
                        left++;
                    }
                    tempLeft = size;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Up0(ArrayList<Object> list, int x, int y, int size) {
        for (Object obj : list) {
            if (obj.x == x) {
                if (obj.y == y - 45 * size) {
                    if (obj instanceof Wall) {
                        tempUp = size - 1;
                        return true;
                    } else {
                        if (obj instanceof Bomb) {
                            ((Bomb) obj).lifeTime = 15;
                            ((Bomb) obj).impactDownBomb = true;
                            endUp = true;
                        } else if (obj instanceof Brick && up == 0) {
                            removeBrick(Game.listObject, (Brick) obj);
                            up++;
                        }
                        tempUp = size;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean Down0(ArrayList<Object> list, int x, int y, int size) {
        for (Object obj : list) {
            if (obj.x == x) {
                if (obj.y == y + 45 * size) {
                    if (obj instanceof Bomb) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactUpBomb = true;
                        endDown = true;
                    } else if (obj instanceof Brick && down == 0) {
                        removeBrick(Game.listObject, (Brick) obj);
                        down++;
                    }
                    tempDown = size;
                    return true;
                }
            }
        }
        return false;
    }

    private void removeMonster(ArrayList<Character> list, Enemy monster) {
        Iterator<Character> ite = list.iterator();
        Character character;
        while (ite.hasNext()) {
            character = ite.next();
            if (character instanceof Enemy) {
                if (character.equals(monster)) {
                    ite.remove();
                    break;
                }
            }
        }
    }
    public static void removeItem(ArrayList<Item> list, Item i) {
        Iterator<Item> ite = list.iterator();
        while (ite.hasNext()) {
            Item it = ite.next();
            if (i.equals(it)) {
                ite.remove();
                break;
            }
        }
    }

    @Override
    public void drawObject(Graphics2D g2) {

            g2.drawImage(img_center_2, x, y, 45, 45, null);


        int i;
        for (i = 1; i <= tempUp; i++) {
            if (!Up0(Game.listObject, this.x, this.y, i) && isDrawUp) {
                if (i == tempUp) {
                    if (!endUp) {
                        g2.drawImage(img_up, x, y - 45 * i, 45, 45, null);
                    } else {
                        g2.drawImage(img_up_2, x, y - 45 * i, 45, 45, null);
                    }
                } else {
                    g2.drawImage(img_up_2, x, y - 45 * i, 45, 45, null);
                }

            } else {
                break;
            }
        }
        for (i = 1; i <= tempDown; i++) {
            if (!Down0(Game.listObject, this.x, this.y, i) && isDrawDown) {
                if (i == tempDown) {
                    if (!endDown) {
                        g2.drawImage(img_down, x, y + 45 * i, 45, 45, null);
                    } else {
                        g2.drawImage(img_down_2, x, y + 45 * i, 45, 45, null);
                    }
                } else {
                    g2.drawImage(img_down_2, x, y + 45 * i, 45, 45, null);
                }

            } else {
                break;
            }
        }
        for (i = 1; i <= tempRight; i++) {
            if (!Right0(Game.listObject, this.x, this.y, i) && isDrawRight) {
                if (i == tempRight) {
                    if (!endRight) {
                        g2.drawImage(img_right, x + 45 * i, y, 45, 45, null);
                    } else {
                        g2.drawImage(img_right_2, x + 45 * i, y, 45, 45, null);
                    }
                } else {
                    g2.drawImage(img_right_2, x + 45 * i, y, 45, 45, null);
                }
            } else {
                break;
            }
        }
        for (i = 1; i <= tempLeft; i++) {
            if (!Left0(Game.listObject, this.x, this.y, i) && isDrawLeft) {
                if (i == tempLeft) {
                    if (!endLeft) {
                        g2.drawImage(img_left, x - 45 * i, y, 45, 45, null);
                    } else {
                        g2.drawImage(img_left_2, x - 45 * i, y, 45, 45, null);
                    }
                } else {
                    g2.drawImage(img_left_2, x - 45 * i, y, 45, 45, null);
                }

            } else {
                break;
            }
        }
    }

    public boolean impactWithBomber() {
        Rectangle rec1 = new Rectangle(this.x, this.y + 45, 40, 40 * tempDown);
        Rectangle rec2 = new Rectangle(this.x, this.y - 45*tempUp, 40, 40 * tempUp);
        Rectangle rec3 = new Rectangle(this.x+45, this.y, 40 * tempRight, 40);
        Rectangle rec4 = new Rectangle(this.x - 45*tempLeft, this.y, 40 * tempLeft, 40);
        if (rec1.intersects(Game.bomber.getBound()) || rec2.intersects(Game.bomber.getBound()) || rec3.intersects(Game.bomber.getBound()) || rec4.intersects(Game.bomber.getBound())) {
            return true;
        }
        return false;
    }

    public void impactWithBomb() {
        Rectangle rec1 = new Rectangle(this.x, this.y + 45, 40, 40 * tempDown);
        Rectangle rec2 = new Rectangle(this.x, this.y - 45 * tempUp, 40, 40 * tempUp);
        Rectangle rec3 = new Rectangle(this.x + 45, this.y, 40 * tempRight, 40);
        Rectangle rec4 = new Rectangle(this.x - 45 * tempLeft, this.y, 40 * tempLeft, 40);
        for (Object object : Game.listObject) {
            if(rec1.intersects(object.getBound()) || rec2.intersects(object.getBound()) || rec3.intersects(object.getBound()) || rec4.intersects(object.getBound()  )) {
                if (object instanceof Bomb) {
                    ((Bomb) object).instantExplose();
                }
            }
        }
    }

    public void impactWithItems() {
        boolean b = false;
        Rectangle rec1 = new Rectangle(this.x, this.y + 45, 40, 40 * tempDown);
        Rectangle rec2 = new Rectangle(this.x, this.y - 45*tempUp, 40, 40 * tempUp);
        Rectangle rec3 = new Rectangle(this.x+45, this.y, 40, 40);
        Rectangle rec4 = new Rectangle(this.x - 45*tempLeft, this.y, 40 * tempLeft, 40);
        for(Item item : Game.listItem){
            if(rec1.intersects(item.getBound()) || rec2.intersects(item.getBound()) || rec3.intersects(item.getBound()) || rec4.intersects(item.getBound()  )){
                for(Object obj : Game.listObject){
                    if(obj instanceof Brick){
                        if(((Brick) obj).isItemInside(item)) {
                            b = true;
                            break;
                        }
                    }
                }
                if(b)removeItem(Game.listItem, item);
                break;
            }
        }
    }

    public void impactWithMonster() {
        Rectangle rec1 = new Rectangle(this.x, this.y + 45, 40, 40 * tempDown);
        Rectangle rec2 = new Rectangle(this.x, this.y - 45*tempUp, 40, 40 * tempUp);
        Rectangle rec3 = new Rectangle(this.x+45, this.y, 40 * tempRight, 40);
        Rectangle rec4 = new Rectangle(this.x - 45*tempLeft, this.y, 40 * tempLeft, 40);
        for(Character character : listEnemy){
            if(character instanceof Enemy){
                if (rec1.intersects(((Enemy) character).getBound()) || rec2.intersects(((Enemy) character).getBound()) || rec3.intersects(((Enemy) character).getBound()) || rec4.intersects(((Enemy) character).getBound())) {
                    removeMonster(listEnemy, (Enemy) character);
                    break;
                }
            }
        }
    }
}
