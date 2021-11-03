/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main.gamescreen;

import com.muhender.spaceconquest.main.*;
import com.muhender.spaceconquest.listeners.ButtonEventManager;
import com.muhender.spaceconquest.sprites.GemColor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author R Muhender Raj
 */
public class Header extends JPanel{
    public JButton play, mainMenu, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    public JLabel resource[];
    
    public Header(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        resource = new JLabel[GemColor.values().length];
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setFocusable(true);
        requestFocus();
        setSize(GamePanel.head.getSize());
        init();
    }  
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 25);
        g2D.setFont(f);
                   
        Rectangle2D fontRect = g2D.getFontMetrics().getStringBounds("Header", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Header", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), this.getHeight() / 2 + (int)(fontRect.getHeight() / 2));
        
    }
    
    protected void init(){
        
        
        for(int i = 0; i < resource.length; i++){
            resource[i] = new JLabel("0", SwingConstants.CENTER);
            resource[i].setForeground(Color.BLACK);
            resource[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            resource[i].setOpaque(true);
            resource[i].setBackground(Color.GRAY);
            resource[i].setFont(new Font("Arial", Font.PLAIN, 20));
            //resource[i].setAlignmentX(CENTER_ALIGNMENT);
            //resource[i].setPreferredSize(new Dimension((int)(this.getHeight() * 0.25), this.getWidth() / 30));
            add(resource[i]);
        }
        
        
        
        
        //TODO do initialisation of objects here
//        ac = new ButtonEventManager();
//        
//        play = new JButton("PLAY");
//        mainMenu = new JButton("MAIN MENU");
//        quit = new JButton("QUIT");
//        
//        play.setActionCommand("start the game");
//        mainMenu.setActionCommand("go to the main menu");
//        quit.setActionCommand("quit the game");
//        
//        play.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        mainMenu.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        quit.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        
//        play.addActionListener(ac);
//        mainMenu.addActionListener(ac);
//        quit.addActionListener(ac);
//        
//        add(play);
//        add(mainMenu);
//        add(quit);
    }
}
