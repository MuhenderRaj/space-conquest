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
public class Upgrades extends JPanel{
    public JButton upgrade1, upgrade2, upgrade3, back;
    private final int buttonWidth, buttonHeight;

    private ActionListener ac;
    
    
    public Upgrades(){
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
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Upgrades", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Upgrades", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    public void init(){
        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        upgrade1 = new JButton("UPGRADE 1");
        upgrade2 = new JButton("UPGRADE 2");
        upgrade3 = new JButton("UPGRADE 3");
        back = new JButton("BACK");
        
        upgrade1.setActionCommand("upgrade 1");
        upgrade2.setActionCommand("upgrade 2");
        upgrade3.setActionCommand("upgrade 3");
        back.setActionCommand("go to game menu");
        
        upgrade1.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        upgrade2.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        upgrade3.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        back.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.8 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        upgrade1.addActionListener(ac);
        upgrade2.addActionListener(ac);
        upgrade3.addActionListener(ac);
        back.addActionListener(ac);
        
        add(upgrade1);
        add(upgrade2);
        add(upgrade3);
        add(back);
    }
}
