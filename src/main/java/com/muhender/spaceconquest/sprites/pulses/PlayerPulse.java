/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites.pulses;

import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.sprites.Player;
import com.muhender.spaceconquest.sprites.Pulse;
import static com.muhender.spaceconquest.sprites.Pulse.HEIGHT;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author R Muhender Raj
 */
public class PlayerPulse extends Pulse{
    
    private int damage = 20;
    public static int speed = 800;
    
    public PlayerPulse(Vector2 position, Vector2 velocity) {
        super(position, velocity);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pulseSprites/PulseSprite.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.position.add(new Vector2(Player.WIDTH - 50, (0.5 * Player.HEIGHT - 0.5 * HEIGHT)));
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
