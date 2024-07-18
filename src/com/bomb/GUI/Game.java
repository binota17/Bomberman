package com.bomb.GUI;

import GameSound.GameSound;
import com.bomb.Background.Background;
import com.bomb.Object.*;
import com.bomb.Object.Object;
import com.bomb.Character.Character;
import com.bomb.Character.*;
import com.bomb.Item.*;
import com.bomb.Object.Panel;
import com.bomb.Status.Live;
import com.bomb.Status.Score;
import com.bomb.Status.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel implements ActionListener {
    Container container;
    static final int D_W = 805;
    private int live = 3;
    public static final int D_H = 665;
    private int bomberX, bomberY;
    private static final int ix = 45;
    private static final int iy = 45;
    public static Bomber bomber;
    private Background background = new Background();
    public static ArrayList<Object> listObject = new ArrayList<>();
    public static ArrayList<Explosion> explosionList = new ArrayList<>();
    public static ArrayList<Character> listEnemy = new ArrayList<>();
    public static ArrayList<Item> listItem = new ArrayList<>();
    public static ArrayList<Portal> listPortal = new ArrayList<>();
    private Camera camera = new Camera(0, 0);
    private int direction;
    static public int framesUp = 0;
    static public int framesDown = 0;
    static public int framesLeft = 0;
    static public int framesRight = 0;
    public boolean nextLevel = false;
    public static boolean isKeyPressed = false;
    private int count = 0;
    private int bombCount = 0;
    public int score = 0;
    public int time = 200;
    public int enemyNumber = listEnemy.size();
    private Score scoreBoard = new Score(this);
    private Live liveBoard = new Live(this);
    private Time timerBoard = new Time(this);


    private String[] map = {"map1.txt", "map2.txt", "map3.txt", "map4.txt"};
    private int numberMap = 0;

    Game(String map, Container container) {
        this.container = container;
        addKeyListener(this.myAdapter);
        setLayout(null);
        loadMap(map);
        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void loadMap(String map) {
        BufferedReader br;
        String s;

        int i, row, line = 0;
        try {
            br = new BufferedReader(new FileReader(map));
            s = br.readLine();
            while (s != null) {
                row = 0;
                for (i = 0; i < s.length(); i++) {
                    switch (s.charAt(i)) {
                        case '#': {
                            this.addObject(new Wall(row * ix, line * iy));
                            break;
                        }
                        case '&': {
                            this.addObject(new Panel(row * ix, line * iy));
                            break;
                        }
                        case '*': {
                            this.addObject(new Brick(row * ix, line * iy));
                            break;
                        }
                        case '1': {
                            listEnemy.add(new Ballom(row * ix, line * iy));
                            break;
                        }
                        case 'p': {
                            if (bomber == null) {
                                bomberX = row * ix;
                                bomberY = line * iy;
                                bomber = new Bomber(row * ix, line * iy);
                                break;
                            } else {
                                bomberX = row * ix;
                                bomberY = line * iy;
                                bomber.x = row * ix;
                                bomber.y = line * iy;
                                break;
                            }
                        }
                        case 'f': {
                            listItem.add(new FlameItem(row * ix, line * iy));
                            break;
                        }
                        case 's': {
                            listItem.add(new SpeedItem(row * ix, line * iy));
                            break;
                        }
                        case 'b': {
                            listItem.add(new BombItem(row * ix, line * iy));
                            break;
                        }
                        case '2': {
                            listEnemy.add(new Oneal(row * ix, line * iy));
                            break;
                        }
                        case '3': {
                            listEnemy.add(new Doll(row * ix, line * iy));
                            break;
                        }
                        case '4': {
                            listEnemy.add(new Minvo(row * ix, line * iy));
                            break;
                        }
                        case 'x': {
                            listPortal.add(new Portal(row * ix, line * iy));
                            break;
                        }
                        case 'S': {
                            listItem.add(new SpeedItem(row*ix,line*iy));
                            this.addObject(new Brick(row*ix,line*iy));
                            break;
                        }
                        case 'F': {
                            listItem.add(new FlameItem(row*ix,line*iy));
                            this.addObject(new Brick(row*ix,line*iy));
                            break;
                        }
                        case 'B':{
                            listItem.add(new BombItem(row*ix,line*iy));
                            this.addObject(new Brick(row*ix,line*iy));
                            break;
                        }
                        case 'm':{
                            listItem.add(new MaxBoomb(row*ix,line*iy));
                            break;
                        }
                        case 'M':{
                            listItem.add(new MaxBoomb(row*ix,line*iy));
                            this.addObject(new Brick(row*ix,line*iy));
                            break;
                        }


                    }
                    row++;
                }
                s = br.readLine();
                line++;
            }
            enemyNumber = listEnemy.size();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(camera.getX(), camera.getY());
        background.drawBackGround(g2d);




        for (Item i : listItem) {
            i.drawItem(g2d);
        }
        for (Object object : listObject) {
            if (object instanceof Brick || object instanceof Bomb) {
                object.drawObject(g2d);
            }
        }
        for (Explosion bombb : explosionList) {
            bombb.drawObject(g2d);
        }
        
        for (Object object : listObject) {
            if (object instanceof Wall || object instanceof Panel) {
                object.drawObject(g2d);
            }
        }
        for (Portal p : listPortal) p.drawPortal(g);

        for (Character character : listEnemy) {
            character.drawCharacter(g2d);
            for (Portal p : listPortal) {
                p.drawPortal(g);
            }
        }
        for (Character character : listEnemy) {
            character.drawCharacter(g2d);
        }
        bomber.drawCharacter(g2d);
        g2d.translate(-camera.getX(), -camera.getY());

        scoreBoard.draw(g2d);
        liveBoard.draw(g2d);
        timerBoard.draw(g2d);
    }

    @Override

    public Dimension getPreferredSize() {
        return new Dimension(D_W, D_H);
    }

    private void addObject(Object object) {
        listObject.add(object);
    }

    private KeyAdapter myAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() != KeyEvent.VK_SPACE) {
                isKeyPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Bomber.direction = 1;
                direction = Character.UP;
                bomber.dy = -bomber.movement;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Bomber.direction = 2;
                direction = Character.DOWN;
                bomber.dy = bomber.movement;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Bomber.direction = 3;
                direction = Character.LEFT;
                bomber.dx = -bomber.movement;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Bomber.direction = 4;
                direction = Character.RIGHT;
                bomber.dx = bomber.movement;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameSound gameSound = new GameSound("./src/GameSound/newbomb.wav");
                boolean isDraw = true;
                Bomb bomb = new com.bomb.Object.Bomb(bomber.x + bomber.getWidth() / 2, bomber.y + bomber.getHeight() / 2, 120);
                for (Object object : listObject) {
                    if (object instanceof Bomb) {
                        if (object.x == bomb.x && object.y == bomb.y) {
                            isDraw = false;
                        }
                    }
                }

                if (isDraw && Game.bomber.currentBomb < Game.bomber.maxBomb) {
                    gameSound.play();
                    addObject(bomb);
                    Game.bomber.currentBomb++;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                count = 10;
                isKeyPressed = false;
                if(bomber.dy == -bomber.movement)bomber.dy = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                count = 10;
                isKeyPressed = false;
                if(bomber.dy == bomber.movement)bomber.dy = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                count = 10;
                isKeyPressed = false;
                if(bomber.dx == -bomber.movement) bomber.dx = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                count = 10;
                isKeyPressed = false;
                if(bomber.dx == bomber.movement)bomber.dx = 0;
            }
        }


    };

    public void checkBomb() {
        Iterator<Object> ite = listObject.iterator();
        while (ite.hasNext()) {
            Object object = ite.next();
            if (object instanceof Bomb) {
                ((Bomb) object).explose();
                if (((Bomb) object).lifeTime <= 0) {
                    int x = object.x;
                    int y = object.y;
                    explosionList.add(new Explosion(x, y, 20 , bomber.bombSize, !((Bomb) object).impactRightBomb, !((Bomb) object).impactLeftBomb, !((Bomb) object).impactDownBomb, !((Bomb) object).impactUpBomb));
                    ite.remove();
                    Game.bomber.currentBomb--;
                }
            }
        }
    }

    public void checkExplosion() {
        Iterator<Explosion> ite = explosionList.iterator();
        while (ite.hasNext()) {
            Explosion explo = ite.next();
            explo.remove();
            if (explo.time <= 0) {
                explo.isRemove = true;
                ite.remove();
                GameSound gameSound = new GameSound("./src/GameSound/explosion.wav");
                gameSound.play();
            }
        }
    }

    public void moveMonster() {
        for (Character character : listEnemy) {
            ((Enemy) character).move();
        }
    }

    public void clearMap() {
        listObject.clear();
        listEnemy.clear();
        listItem.clear();
        listPortal.clear();
    }
    public void init() {
        bomber.isAlive = true;
        bomber.maxBomb = 1;
        bomber.bombSize = 1;
        bomber.movement = 4;
        bomber.x = bomberX;
        bomber.y = bomberY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (live == 0) {
            clearMap();
            loadMap(map[0]);
            numberMap = 0;
            live = 3;
        }
        if(!bomber.isAlive) {
            listObject.removeIf(object -> object instanceof Bomb);
            init();
        }

        if (nextLevel) {
            nextLevel = false;
            clearMap();
            numberMap++;
            loadMap(map[numberMap]);

        }

        if (isKeyPressed) {
            count++;
            if (count >= 10) {
                count = 0;
                if (Bomber.direction == 1) {
                    framesUp++;
                }
                if (Bomber.direction == 2) {
                    framesDown++;
                }
                if (Bomber.direction == 3) {
                    framesLeft++;
                }
                if (Bomber.direction == 4) {
                    framesRight++;
                }
            }
        }
        bombCount++;
        if (bombCount == 20) {
            bombCount = 0;
            for (Object object : listObject) {
                if (object instanceof Bomb) {
                    ((Bomb) object).framesBomb++;
                }
            }
            for (Item it : listItem){
                it.frames++;
            }
            if(!bomber.isAlive) bomber.frameDead++;
        }
        for (Character character : listEnemy) {
            if (character instanceof Enemy) {
                ((Enemy) character).changeDirection();
            }
        }
        for (Item it : listItem) {
            if (bomber.insertItem(it)) {
                Explosion.removeItem(listItem, it);
                break;
            }

        }
        for (Portal p : listPortal) {
            if (bomber.isInsertPortal(p)  /* && listMonster.isEmpty() */) {
                nextLevel = true;
                break;
            }
        }
        for (Explosion object : explosionList) {
            object.impactWithItems();
            object.impactWithMonster();
            updateScore();
        }
        for (Explosion object : explosionList) {
            if (object.impactWithBomber()) {
                bomber.isAlive = false;
                live --;
                break;
            }
        }
        for (Explosion explosion : explosionList) {
            explosion.impactWithBomb();
        }
        for (Character m : listEnemy) {
            if (m instanceof Enemy) {
                if (bomber.impactWithMonster((Enemy) m)) {
                    bomber.isAlive = false;
                    live--;
                    break;
                }
            }
        }
        bomber.move();
        bomber.changeDirection(direction);
        camera.moveCamera(bomber);
        moveMonster();
        checkBomb();
        checkExplosion();
        repaint();
        updateTimer();
    }

    public void updateScore() {
        if (listEnemy.size() < enemyNumber) {
            score += (enemyNumber - listEnemy.size()) * 100;
        }
    }

    public void updateTimer() {
        long timer = System.currentTimeMillis();
        if(System.currentTimeMillis() - timer > 1000) { //once per second
            time--;
        }
    }

    public int getNumberMap() {
        return numberMap;
    }

    public int getLive() {
        return live;
    }
}