/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.interfaces;

import com.muhender.spaceconquest.math.Vector2;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * An interface to represent a 2D sprite
 * @author R Muhender Raj
 */
public interface Sprite {
    Vector2 getPosition();
    void setVelocity(Vector2 v);
    Vector2 getVelocity();
    void addForce(Vector2 v);
    /**
     * Draws the sprite to the current graphics2D object
     * @param g2D the graphics2D object
     * @param i the ImageObserver
     */
    void drawSprite(Graphics2D g2D, ImageObserver i);
    /**
     * Updates the position of the sprite
     */
    void updatePosition();
    /**
     * Gets the bounding rectangle of this sprite object
     * @return the collider as a Rectangle object
     */
    Rectangle getCollider();
}
