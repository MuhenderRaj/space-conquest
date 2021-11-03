/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Animates the sprites
 * @author R Muhender Raj
 */
public class Animation {
    private final BufferedImage sprites[];
    private final int duration;
    private final boolean isLoop;
    private final int totalFrames;
    private boolean running;
    private int currentFrame;
    private List<AnimationFrame> frameList = new ArrayList<>();
    private int counter;
    
    public Animation(BufferedImage spriteSheet, int duration, boolean isLoop){
        sprites = new SpriteSheetCutter(spriteSheet).getFrames();
        for(BufferedImage b: sprites){
            frameList.add(new AnimationFrame(b, duration));
        }
        this.isLoop = isLoop;
        this.duration = duration;
        this.totalFrames = sprites.length;
        currentFrame = 0;
        running = false;
        counter = 0;
    }
    
    public void startAnimation(){
        running = true;
    }
    
    public void updateAnimation(){
        if(running){
            counter++;
            if(counter >= duration){
                currentFrame++;
                counter = 0;
            }
            if(currentFrame >= totalFrames){
                if(isLoop) currentFrame = 0;
                else{ 
                    currentFrame = totalFrames - 1;
                    stopAnimation();
                }
            }
        }
    }
    
    public void stopAnimation(){
        running = false;
    }
    
    public void resetAnimation(){
        counter = 0;
        currentFrame = 0;
        running = false;
    }
    
    
    public BufferedImage getCurrentImage(){
        return frameList.get(currentFrame).getCurrentImage();
    }
}
