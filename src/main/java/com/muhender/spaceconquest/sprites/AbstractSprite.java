/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * An abstract class for implementing most of the common features of all sprites
 * @author R Muhender Raj
 */
public abstract class AbstractSprite implements Sprite{
    protected Vector2 position, velocity, force;

    private int WIDTH, HEIGHT;
    public static int speed = 250;
    
    protected AbstractSprite(Vector2 position, Vector2 velocity, Vector2 force,final int WIDTH, final int HEIGHT){
        this.position = position;
        this.velocity = velocity;
        this.force = force;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        
    }
    protected AbstractSprite(final int WIDTH, final int HEIGHT){
        this(null, null, null, WIDTH, HEIGHT);
    }
    
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }    

    @Override
    public void addForce(Vector2 force) {
        this.force.add(force);
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }
    

    @Override
    public abstract void drawSprite(Graphics2D g2D, ImageObserver i);
    
    @Override
    public abstract void updatePosition();


    @Override
    public Rectangle getCollider(){
        return new Rectangle((int)position.getX(), (int)position.getY(), WIDTH, HEIGHT);
    }
}
