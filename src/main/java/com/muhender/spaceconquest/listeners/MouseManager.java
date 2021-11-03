/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.listeners;

import com.muhender.spaceconquest.main.gamescreen.MatchBoard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author R Muhender Raj
 */
public class MouseManager implements MouseMotionListener, MouseListener{
   MatchBoard level;
   public boolean hasInMemory = false;
    
    public MouseManager(MatchBoard level){
        this.level = level;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        //Write implementation here
        //System.out.println("I, void mouseDragged, don't work yet");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Write implementation here
        //System.out.println("I, void mouseMoved, don't work yet");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(hasInMemory)
            level.released(e.getPoint());
        else
            level.pressed(e.getPoint());
        hasInMemory = !hasInMemory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Write implementation here
        //System.out.println("I, void mouseEntered, don't work yet");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Write implementation here
        //System.out.println("I, void mouseExited, don't work yet");
    }
}
