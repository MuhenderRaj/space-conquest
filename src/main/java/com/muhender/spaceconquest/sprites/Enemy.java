/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import com.muhender.spaceconquest.interfaces.Animatable;
import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author R Muhender Raj
 */
public abstract class Enemy extends AbstractSprite implements Sprite, Animatable{
    protected BufferedImage image;
    public static int enemyCount = 0;
    public static int WIDTH = 100, HEIGHT = 80;
    protected Animation shooting;
    protected Animation moving;
    protected int health;
    public static int speed = 250;
    
    protected Animation currentAnimationState;
    public int damage = 20;
    
    public Enemy(Vector2 position, Vector2 velocity){
        super(position, velocity, new Vector2(0, 0), WIDTH, HEIGHT);
        
    }
    
    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        if(image != null)
            g2D.drawImage(currentAnimationState.getCurrentImage(), (int)position.getX(), (int)position.getY(), WIDTH, HEIGHT, i);
    }
    
    @Override
    public void updatePosition(){
        currentAnimationState.updateAnimation();
        position.add(velocity.scale(Constants.deltaTime / 1000));
        if(force != null)
            velocity.add(force.scale(Constants.deltaTime / 1000));
    }
    
    /**
     * Changes the animation state
     * 1 - moving
     * 2 - shooting
     * @param i the animation
     */
    @Override
    public void changeAnimation(int i){
        currentAnimationState.resetAnimation();
        
        switch(i){
            case 1:
                currentAnimationState = moving;
                break;
            case 2:
                currentAnimationState = shooting;
                break;
        }
        
        currentAnimationState.startAnimation();
    }

    @Override
    public int getCurrentAnimation() {
        if(currentAnimationState == moving) return 1;
        else if(currentAnimationState == shooting) return 2;
        return 0;
    }

    /**
     * @return the health of the enemy
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param deltaHealth the health of the enemy to be reduced
     */
    public void reduceHealth(int deltaHealth) {
        this.health -= deltaHealth;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public abstract int getXPIncrease();
}
