/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.listeners;

import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.LoadGame;
import com.muhender.spaceconquest.main.gamescreen.GameField;
import com.muhender.spaceconquest.math.Vector2;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author R Muhender Raj
 */
public class MouseEventManager extends MouseAdapter{

    private JPanel panel;
    
    public MouseEventManager(JPanel panel){
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(Constants.shootLevel >=3){
            if(panel instanceof GameField)
                ((GameField)panel).instantiateLaser(Vector2.position(e.getPoint()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(panel instanceof GameField)
            ((GameField)panel).laserActive = false;
    }
    
}
