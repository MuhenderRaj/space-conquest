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
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The class for the player sprite
 * @author R Muhender Raj
 */
public class Player extends AbstractSprite implements Sprite, Animatable{

    public static final int WIDTH = 120, HEIGHT = 80;
    private BufferedImage image;
    private Animation moveUp;
    private Animation moveDown;
    private Animation idle;
    private int health = 100;
    private int maxHealth = 100;
    public Animation currentAnimationState;
    private int XP = 0;
    public static int speed = 250;
    
    public Player(Vector2 position, Vector2 velocity){
        super(position, velocity, new Vector2(0, 0), WIDTH, HEIGHT);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerSprites/PlayerSprite.jpg"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        idle = new Animation(image, 12, true);
        moveUp = new Animation(image, 12, false);
        moveDown = new Animation(image, 12, false);
        
        currentAnimationState = idle;
        
        currentAnimationState.startAnimation();
    }
    
    
    
    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {

        

        if(image != null)
            g2D.drawImage(currentAnimationState.getCurrentImage(), (int)position.getX(), (int)position.getY(), WIDTH, HEIGHT, i);
    }
    
    @Override
    public void updatePosition() { //TODO do something about the Constants.WIDTH
        currentAnimationState.updateAnimation();
        if((position.getX() < 0 && velocity.getX() < 0) || (position.getX() > Constants.WIDTH - WIDTH && velocity.getX() > 0))
            velocity.setX(0);
        
        if((position.getY() < 0 && velocity.getY() < 0) || (position.getY() > Constants.HEIGHT - HEIGHT && velocity.getY() > 0))
            velocity.setY(0);
        
        position.add(velocity.scale(Constants.deltaTime / 1000));
        if(force != null)
            velocity.add(force.scale(Constants.deltaTime / 1000));
    }
    /**
     * gets the id of the animation state
     * @return the id of the animation
     */
    @Override
    public int getCurrentAnimation() {
        if(currentAnimationState == idle) return 1;
        else if(currentAnimationState == moveUp) return 2;
        else if(currentAnimationState == moveDown) return 3;
        return 0;
    }

    /**
     * Changes the animation state<br>
     * 1 - idle<br>
     * 2 - moving up<br>
     * 3 - moving down<br>
     * @param i the id of the state
     */
    @Override
    public void changeAnimation(int i) {
        currentAnimationState.resetAnimation();
        
        switch(i){
            case 1:
                currentAnimationState = idle;
                break;
            case 2:
                currentAnimationState = moveUp;
                break;
            case 3:
                currentAnimationState = moveDown;
                break;
        }
        
        currentAnimationState.startAnimation();
    }

    /**
     * @return the health of the player
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param deltaHealth the amount by which the health is to be reduced
     */
    public void reduceHealth(int deltaHealth) {
        this.health -= deltaHealth;
    } 

    /**
     * @return the experience points of the player
     */
    public int getXP() {
        return XP;
    }

    /**
     * @param XP The increase in experience points
     */
    public void increaseXP(int XP) {
        this.XP += XP;
    }

    /**
     * @return the maximum health of the player
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param deltaHealth the amount by which the maximum health of the player is increased
     */
    public void increaseMaxHealth(int deltaHealth) {
        this.maxHealth += deltaHealth;
    }

    
}
