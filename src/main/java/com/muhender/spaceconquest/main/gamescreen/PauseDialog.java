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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * TODO Work more on this later
 * @author R Muhender Raj
 */
public class PauseDialog extends JPanel implements Runnable{
    public JButton resume, saveQuit, restart;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    public static Thread thread;
    
    public PauseDialog(){
        super();
        buttonWidth = 200;
        buttonHeight = 60;
        //setBackground(new Color(0, 0, 0, 40));
        
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(Game.game.getSize());
        init();
        if(thread != null)
            thread.interrupt();
        
        thread = new Thread(this, "Pause");
        thread.start();
    }  
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        Font f = new Font("Arial", 30, 30);
        g2D.setFont(f);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Paused", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Lorem Ipsum", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), this.getHeight() / 5 + (int)(fontRect.getHeight() / 2));
        
        g2D.setColor(new Color(200,200,200));
        int boxWidth = (int)(0.4 * this.getWidth());
        int boxHeight = (int)(0.8 * this.getHeight());
        g2D.fillRect((this.getWidth() - boxWidth) / 2, (this.getHeight() - boxHeight) / 2, boxWidth, boxHeight);
        
    }
    
    protected void init(){

        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        
        resume = new JButton("RESUME");
        saveQuit = new JButton("QUIT TO MENU");
        restart = new JButton("RESTART LEVEL");
        
        resume.setActionCommand("resume the game");
        saveQuit.setActionCommand("go to game menu");
        restart.setActionCommand("restart the level");
        
        ac = new ButtonEventManager();
        
        resume.addActionListener(ac);
        saveQuit.addActionListener(ac);
        restart.addActionListener(ac);
        
        resume.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        saveQuit.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        restart.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        add(resume);
        add(saveQuit);
        add(restart);
    }
    
    @Override
    public void run(){
        long startTime;
        double timePerFrame;
        long sleepTime;
        System.out.println("Hey");
//        try {
//            thread.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PauseDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Thread[] t = new Thread[Thread.activeCount() + 1];
//        Thread.enumerate(t);
//        java.util.Arrays.stream(t).forEach((k) -> System.out.println(k.getName()));
        
        do{
            startTime = System.nanoTime();
            timePerFrame = (System.nanoTime() - startTime)/1000000;
            sleepTime = (long)(Constants.deltaTime - timePerFrame);
            if(sleepTime < 0){ 
                System.out.println("OverworkedPause");
                sleepTime = 5;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } 
            repaint();
            requestFocus();
        }while(Constants.isPaused);
        Game.game.getContentPane().revalidate();
    }
}
