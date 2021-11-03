/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites.enemies;

import com.muhender.spaceconquest.interfaces.Animatable;
import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.main.gamescreen.GameField;
import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.sprites.Animation;
import com.muhender.spaceconquest.sprites.Enemy;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * These guys are slow, but durable. It takes a lot to take them down
 * @author R Muhender Raj
 */
public class ShieldedEnemy extends Enemy implements Sprite, Animatable{
    
    private long time;
    public int XPGain = 80;
    private int damage = 80;
    public static int speed = 40; 
    
    public ShieldedEnemy(Vector2 position, Vector2 velocity, GameField level) {
        super(position, velocity);
        try {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemySprites/EnemySprite.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        enemyCount++;
        
        health = 120;
        moving = new Animation(image, 12, true);
        
        currentAnimationState = moving;
        
        currentAnimationState.startAnimation();
        time = System.currentTimeMillis();
    }
    
    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        super.drawSprite(g2D, i);
        
        
    }
    
    @Override
    public void updatePosition(){
        super.updatePosition();
    }

    @Override
    public int getXPIncrease() {
        return XPGain;
    }
    @Override
    public int getDamage(){
        return damage;
    }
}
