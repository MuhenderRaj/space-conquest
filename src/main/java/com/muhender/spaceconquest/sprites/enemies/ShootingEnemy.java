/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites.enemies;

import com.muhender.spaceconquest.interfaces.Animatable;
import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.gamescreen.GameField;
import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.sprites.Animation;
import com.muhender.spaceconquest.sprites.Enemy;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This enemy is a slightly upgraded version of the basic enemy. It can shoot pulses.
 * @author R Muhender Raj
 */
public class ShootingEnemy extends Enemy implements Sprite, Animatable{
    
    private long time;
    private GameField level;
    public int XPGain = 50;
    private int damage = 40;
    public static int speed = 90;
    
    public ShootingEnemy(Vector2 position, Vector2 velocity, GameField level) {
        super(position, velocity);
        try {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemySprites/EnemySprite.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        enemyCount++;
        
        health = 40;
        moving = new Animation(image, 12, true);
        
        currentAnimationState = moving;
        
        currentAnimationState.startAnimation();
        time = System.currentTimeMillis();
        this.level = level;
    }
    
    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        super.drawSprite(g2D, i);
        
        
    }
    
    @Override
    public void updatePosition(){
        super.updatePosition();
        if((System.currentTimeMillis() - time) > Constants.shooterTimeDelay){
            if(Constants.random.nextDouble() > 0.2)
                level.instantiateEnemyPulse(this);
            time = System.currentTimeMillis();
        }
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
