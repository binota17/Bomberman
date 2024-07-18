package com.bomb.Character;

import java.awt.*;

public abstract class Enemy extends Character implements CanMove {
    public abstract void changeDirection();
    public abstract Rectangle getBound();
}
