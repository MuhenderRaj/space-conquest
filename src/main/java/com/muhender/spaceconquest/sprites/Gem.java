/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.sprites;

import com.muhender.spaceconquest.interfaces.Sprite;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * fix this!!!
 * @author R Muhender Raj
 */
public class Gem extends AbstractSprite implements Sprite{

    public GemColor color;
    public static int WIDTH = 50, HEIGHT = 50;
    public int noOfSimilarNeighbours = 0;
    private BufferedImage image;
    
    public Gem(GemColor color) {
        super(50, 50);
        this.color = color;
        try {
            initImage();
        } catch (IOException ex) {
            Logger.getLogger(Gem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initImage() throws IOException{
        switch(color){
            case BLUE:
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gems/blueGem.png"));
                break;
            case RED:
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gems/redGem.png"));
                break;
            case GREEN:
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gems/greenGem.png"));
                break;
            case YELLOW:
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gems/yellowGem.png"));
                break;
            default:
                //not possible
        }
    }

    @Override
    public void drawSprite(Graphics2D g2D, ImageObserver i) {
        g2D.drawImage(image, (int)getPosition().getX(), (int)getPosition().getY(), WIDTH, HEIGHT, i);//note. Deleted x&y coord refs. Call updateposition in panel
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    
    public void updatePosition(int x, int y) {
        position = new Vector2(x, y);       
    }

    @Override
    public void updatePosition() {
        //Write implementation here
        System.out.println("I, void updatePosition, don't work yet");
    }
}
