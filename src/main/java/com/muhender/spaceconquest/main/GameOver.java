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
public class GameOver extends JPanel{
    public JButton replay, menu, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    
    public GameOver(){
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
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g2D.getFontMetrics().getStringBounds("Game Over!", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Game Over!", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
        f = new Font("Arial", 15, 15);
        g2D.setFont(f);
        fontRect = g2D.getFontMetrics().getStringBounds("Your health decreased to 0!", g2D);
        g2D.drawString("Your health decreased to 0!", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 250 + (int)(fontRect.getHeight() / 2));
        
    }
    
    protected void init(){

        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        
        replay = new JButton("REPLAY");
        menu = new JButton("MENU");
        quit = new JButton("QUIT");
        
        replay.setActionCommand("replay the game");
        menu.setActionCommand("go to game menu");
        quit.setActionCommand("save and quit the game");
        
        replay.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        menu.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        replay.addActionListener(ac);
        menu.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(replay);
        add(menu);
        add(quit);
    }
}
