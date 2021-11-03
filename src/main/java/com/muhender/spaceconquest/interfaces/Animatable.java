/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.interfaces;


/**
 *
 * @author R Muhender Raj
 */
public interface Animatable {
    /**
     * 
     * @return the current animation state as an integer value denoting the ID 
     */
    public int getCurrentAnimation();
    /**
     * Changes the animation to the given animation ID 
     * @param i the animation ID
     */
    public void changeAnimation(int i);
}
