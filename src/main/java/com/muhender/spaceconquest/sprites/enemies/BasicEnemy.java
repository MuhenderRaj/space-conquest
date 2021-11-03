/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites.enemies;

import com.muhender.spaceconquest.interfaces.Animatable;
import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.sprites.Animation;
import com.muhender.spaceconquest.sprites.Enemy;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The most basic enemy available. It can't shoot. It dies in a single shot. The only threat is having it collide with your ships.
 * @author R Muhender Raj
 */
public class BasicEnemy extends Enemy implements Sprite, Animatable{
    
    private int XPGain = 30;
    private int damage = 20; 
    public static int speed = 100;
    
    public BasicEnemy(Vector2 position, Vector2 velocity) {
        super(position, velocity);
        try {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemySprites/EnemySprite.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        enemyCount++;
        
        health = 10;
        moving = new Animation(image, 12, true);
        
        currentAnimationState = moving;
        
        currentAnimationState.startAnimation();
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
