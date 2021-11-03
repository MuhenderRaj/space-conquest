/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main.gamescreen;

import com.muhender.spaceconquest.main.*;
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
public class Things extends JPanel{
    public JButton play, mainMenu, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    
    public Things(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        setBackground(Color.GREEN);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(GamePanel.things.getSize());
        init();
    }  
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Things", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Things", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    protected void init(){

        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        
        play = new JButton("PLAY");
        mainMenu = new JButton("MAIN MENU");
        quit = new JButton("QUIT");
        
        play.setActionCommand("start the game");
        mainMenu.setActionCommand("go to the main menu");
        quit.setActionCommand("quit the game");
        
        play.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        mainMenu.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        play.addActionListener(ac);
        mainMenu.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(play);
        add(mainMenu);
        add(quit);
    }
}
