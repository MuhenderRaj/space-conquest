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
import javax.swing.JTextField;

/**
 *
 * @author R Muhender Raj
 */
public class CreatePlayer extends JPanel{
    public JButton done, back, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    private JTextField nameField;
    private final int textFieldHeight;
    
    public CreatePlayer(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(Game.game.getSize());
        textFieldHeight = 30;
        init();
    }  
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Create new player", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Create new player", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    public void getPlayerName(){
        String name = nameField.getText();
        Constants.name = name;
    }
    
    protected void init(){

        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager(this);
        
        nameField = new JTextField();
        
        done = new JButton("DONE");
        back = new JButton("MAIN MENU");
        quit = new JButton("QUIT");
        
        done.setActionCommand("go to game menu after saving");
        back.setActionCommand("go to the main menu");
        quit.setActionCommand("quit the game");
        
        Font font = new Font("Arial", Font.PLAIN, 10);
        
        nameField.setFont(font);
        
        nameField.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.4 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);
        
        done.setBounds((this.getWidth() / 2 - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        back.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds(3 * (this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        done.addActionListener(ac);
        back.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(nameField);
        
        add(done);
        add(back);
        add(quit);
    }
}
