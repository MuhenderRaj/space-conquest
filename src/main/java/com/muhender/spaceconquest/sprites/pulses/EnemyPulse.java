/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites.pulses;

import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.sprites.Pulse;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author R Muhender Raj
 */
public class EnemyPulse extends Pulse{
    
    private int damage = 20;
    public static int speed  = 200;
    
    public EnemyPulse(Vector2 position, Vector2 velocity) {
        super(position, velocity);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pulseSprites/PulseSprite.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        super.drawSprite(g2D, i);
    }
    
    @Override
    public void updatePosition(){
        super.updatePosition();
    }
    
    public int getDamage(){
        return damage;
    }
}