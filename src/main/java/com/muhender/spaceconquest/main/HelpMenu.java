/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import com.muhender.spaceconquest.listeners.ButtonEventManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author R Muhender Raj
 */
public class HelpMenu extends JPanel{
    public JButton back;
    private final int buttonWidth, buttonHeight;

    private ActionListener ac;
    
    
    public HelpMenu(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(Game.game.getSize());
        init();
    } 

    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Help", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Help", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    public void init(){
        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        
        back = new JButton("BACK");
        back.setActionCommand("go to the main menu");
        back.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.8 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        back.addActionListener(ac);
        add(back);
    }
}
