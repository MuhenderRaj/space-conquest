/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import java.awt.image.BufferedImage;

/**
 * This class stores individual frames of an animation
 * @author R Muhender Raj
 */
public class AnimationFrame {
    private BufferedImage currentImage;
    private int duration;
    
    
    AnimationFrame(BufferedImage currentImage, int duration){
        this.currentImage = currentImage;
        this.duration = duration;
        
    }

    /**
     * @return the current frame duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return the current frame image
     */
    public BufferedImage getCurrentImage() {
        return currentImage;
    }
    
}
