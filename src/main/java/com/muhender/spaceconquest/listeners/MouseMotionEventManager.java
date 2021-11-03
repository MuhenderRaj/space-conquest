/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.listeners;

import com.muhender.spaceconquest.main.gamescreen.GameField;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author R Muhender Raj
 */
public class MouseMotionEventManager implements MouseMotionListener{

    private GameField panel;
    
    public MouseMotionEventManager(GameField panel){
        this.panel = panel;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(panel.laserActive){
            panel.instantiateLaser(Vector2.position(e.getPoint()));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //TODO Write implementation here
    }
    
}
