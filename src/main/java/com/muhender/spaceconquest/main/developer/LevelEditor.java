/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main.developer;

import com.muhender.spaceconquest.listeners.ButtonEventManager;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.utilities.GameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author R Muhender Raj
 */
public class LevelEditor extends JPanel{
    public JButton addLevel, rewriteLevel, quit;
    public JTextField basic, seeking, shooting, shielded, level;
    private final int buttonWidth, buttonHeight;
    private final int textFieldHeight;
    private ActionListener ac;
    
    public LevelEditor(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        textFieldHeight = 30;
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        
        init();
    }  
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Level Editor", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Level Editor", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
        f = new Font("Arial", Font.PLAIN, 20);
        g2D.setFont(f);
        fontRect = g.getFontMetrics().getStringBounds("Basic enemies", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Basic enemies", this.getWidth() / 3 - (int)(fontRect.getWidth() / 2),(int)(0.4 * this.getHeight() + (int)(fontRect.getHeight() / 2)));
        
        f = new Font("Arial", Font.PLAIN, 20);
        g2D.setFont(f);
        
        fontRect = g.getFontMetrics().getStringBounds("Seeking enemies", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Seeking enemies", this.getWidth() / 3 - (int)(fontRect.getWidth() / 2), (int)(0.45 * this.getHeight() + (int)(fontRect.getHeight() / 2)));
        
        f = new Font("Arial", Font.PLAIN, 20);
        g2D.setFont(f);
        
        fontRect = g.getFontMetrics().getStringBounds("Shooting enemies", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Shooting enemies", this.getWidth() / 3 - (int)(fontRect.getWidth() / 2), (int)(0.5 * this.getHeight() + (int)(fontRect.getHeight() / 2)));
        
        f = new Font("Arial", Font.PLAIN, 20);
        g2D.setFont(f);
        
        fontRect = g.getFontMetrics().getStringBounds("Shielded enemies", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Shielded enemies", this.getWidth() / 3 - (int)(fontRect.getWidth() / 2), (int)(0.55 * this.getHeight() + (int)(fontRect.getHeight() / 2)));
        
        f = new Font("Arial", Font.PLAIN, 20);
        g2D.setFont(f);
        
        fontRect = g.getFontMetrics().getStringBounds("Level to rewrite(if applicable)", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Level to rewrite(if applicable)", this.getWidth() / 3 - (int)(fontRect.getWidth() / 2), (int)(0.6 * this.getHeight() + (int)(fontRect.getHeight() / 2)));
        
    }
    
    public void getNumbers(){
        int bas = Integer.parseInt(basic.getText());
        int see = Integer.parseInt(seeking.getText());
        int sho = Integer.parseInt(shooting.getText());
        int shi = Integer.parseInt(shielded.getText());
        
        
        try {
            GameManager.createLevel(bas, see, sho, shi);
        } catch (IOException ex) {
            Logger.getLogger(LevelEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getLevel(){
        int lev = Integer.parseInt(level.getText());
        int bas = Integer.parseInt(basic.getText());
        int see = Integer.parseInt(seeking.getText());
        int sho = Integer.parseInt(shooting.getText());
        int shi = Integer.parseInt(shielded.getText());
        
        try {
            GameManager.rewriteLevel(lev, bas, see, sho, shi);
        } catch (IOException ex) {
            Logger.getLogger(LevelEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void init(){
        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager(this);
        
        basic = new JTextField();
        seeking = new JTextField();
        shooting = new JTextField();
        shielded = new JTextField();
        level = new JTextField();
        
        Font font = new Font("Arial", Font.PLAIN, 10);
        
        basic.setFont(font);
        seeking.setFont(font);
        shooting.setFont(font);
        shielded.setFont(font);
        level.setFont(font);
        
//        basic.setForeground(Color.BLACK);
//        seeking.setForeground(Color.BLACK);
//        shooting.setForeground(Color.BLACK);
//        shielded.setForeground(Color.BLACK);        
        
        //play = new JButton("PLAY");
        //mainMenu = new JButton("MAIN MENU");
        addLevel = new JButton("ADD LEVEL");
        rewriteLevel = new JButton("REWRITE LEVEL");
        quit = new JButton("QUIT");
        
        
        
        //play.setActionCommand("start the game");
        //mainMenu.setActionCommand("go to the main menu");
        addLevel.setActionCommand("add the level");
        rewriteLevel.setActionCommand("rewrite a level");
        quit.setActionCommand("quit the game");
        
        basic.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.4 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);
        seeking.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.45 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);
        shooting.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.5 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);
        shielded.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.55 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);
        level.setBounds(2 * this.getWidth() / 3 - buttonWidth / 2, (int)(0.6 * this.getHeight() - textFieldHeight / 2), buttonWidth, textFieldHeight);

        //play.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        //mainMenu.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        addLevel.setBounds((this.getWidth() / 2 - buttonWidth) / 2, (int)(0.8 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        rewriteLevel.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.8 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds((3 * this.getWidth() / 2 - buttonWidth) / 2, (int)(0.8 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        
        //play.addActionListener(ac);
        //mainMenu.addActionListener(ac);
        addLevel.addActionListener(ac);
        rewriteLevel.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(basic);
        add(seeking);
        add(shielded);
        add(shooting);
        add(level);
        
        //add(play);
        //add(mainMenu);
        add(addLevel);
        add(rewriteLevel);
        add(quit);
    }
}
