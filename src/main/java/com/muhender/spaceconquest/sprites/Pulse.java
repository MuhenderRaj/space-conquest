/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * The class for the pulse sprite
 * @author R Muhender Raj
 */
public class Pulse extends AbstractSprite implements Sprite{
    
    public static int WIDTH = 20, HEIGHT = 20;
    protected BufferedImage image;
    public static int speed = 500;
    
    public Pulse(Vector2 position, Vector2 velocity){
        super(position, velocity, new Vector2(0, 0), WIDTH, HEIGHT);
        this.position = position;

        this.velocity = velocity;

    }

    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        g2D.drawImage(image, (int)position.getX(), (int)position.getY(), WIDTH, HEIGHT, i);
    }

    @Override
    public void updatePosition() {
        position.add(velocity.scale(Constants.deltaTime / 1000));
    }

    
}
