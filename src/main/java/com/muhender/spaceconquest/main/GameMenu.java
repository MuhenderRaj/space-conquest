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
public class GameMenu extends JPanel{
    public JButton play, enemyInfo, upgrades, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    
    
    public GameMenu(){
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
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Space Game", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Space Game", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    private void init(){
        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        play = new JButton("PLAY");
        enemyInfo = new JButton("ENEMY INFORMATION");
        upgrades = new JButton("UPGRADES");
        quit = new JButton("QUIT TO MAIN MENU");
        
        play.setActionCommand("start the game");
        enemyInfo.setActionCommand("see the enemy info");
        upgrades.setActionCommand("go to upgrades");
        quit.setActionCommand("save and go to the main menu");
        //System.out.println(this.getWidth());
        play.setBounds(this.getWidth() / 4 - buttonWidth / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        enemyInfo.setBounds(this.getWidth() / 4 - buttonWidth / 2, (int)(0.55 *this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        upgrades.setBounds(this.getWidth() * 3 / 4 - buttonWidth / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds(this.getWidth() * 3 / 4 - buttonWidth / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        play.addActionListener(ac);
        enemyInfo.addActionListener(ac);
        upgrades.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(play);
        add(enemyInfo);
        add(upgrades);
        add(quit);
    }
}
