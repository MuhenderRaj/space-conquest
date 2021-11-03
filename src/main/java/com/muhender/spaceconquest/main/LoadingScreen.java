/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This is the generic loading screen shown before starting a level, to allow level resources to load
 * @author R Muhender Raj
 */
public class LoadingScreen extends JPanel implements Runnable{
    private Thread thread;
    public JButton play, mainMenu, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    JPanel destination;
    
    public LoadingScreen(JPanel destination){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(Game.game.getSize());
        this.destination = destination;
    }  
    
    @Override
    public void addNotify(){
        super.addNotify();
        if(thread == null)
            thread = new Thread(this, "Loading");
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Loading", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Loading", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    protected void init(){

        
        
    }

    @Override
    public void run() {
        long startTime;
        double TimePerFrame;
        long sleepTime;
        init();
        
        while(Game.game.getContentPane() instanceof LoadingScreen){
            startTime = System.nanoTime();
            requestFocus();
            
            TimePerFrame = (System.nanoTime() - startTime)/1000000;
            sleepTime = (long)(Constants.deltaTime - TimePerFrame);
            if(sleepTime < 0) sleepTime = 5;
            
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
    }
}
