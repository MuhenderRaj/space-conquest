/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import java.awt.image.BufferedImage;

/**
 * Cuts up a 3*3 spritesheet grid into sprite images
 * @author R Muhender Raj
 */
public class SpriteSheetCutter {
    private int frameWidth, frameHeight, width, height;
    private BufferedImage spriteSheet;
    private BufferedImage images[] = new BufferedImage[9];
    
    SpriteSheetCutter(BufferedImage spriteSheet){
        width = spriteSheet.getWidth();
        height = spriteSheet.getHeight();
        frameWidth = frameWidth = width / 3;
        frameHeight = height / 3;
        this.spriteSheet = spriteSheet;
        cut();
    }
    
    private void cut(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                images[3*i+j] = spriteSheet.getSubimage(j * frameWidth, i * frameHeight, frameWidth, frameHeight);
            }
        }
    }
    
    public BufferedImage[] getFrames(){
         return images;
    }
}
