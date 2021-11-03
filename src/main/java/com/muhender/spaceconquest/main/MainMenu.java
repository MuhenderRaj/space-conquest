/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import com.muhender.spaceconquest.listeners.ButtonEventManager;
import static com.muhender.spaceconquest.main.Constants.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;


/**
 *
 * @author R Muhender Raj
 */
public class MainMenu extends JPanel{
    
    private Random r;
    public JButton play, savedGames, help, quit;
    
    private ActionListener ac;
    
    
    public MainMenu(){
        super();
        
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
        init();
        createLayout();
    } 

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        g2D.setColor(Color.YELLOW);
        
        for(int i = 0; i<1000; i++){
            int starX = r.nextInt(this.getWidth());
            int starY = r.nextInt(this.getHeight());
            //g2D.drawLine(starX, starY, starX, starY);
            g2D.fillOval(starX, starY, 2, 2);
        }
    }
    
    private void init(){
        r = new Random();
        
        
        //TODO do initialisation of objects here
        
        
        ac = new ButtonEventManager();
        
        play = new JButton("NEW GAME");
        savedGames = new JButton("LOAD GAME");
        help = new JButton("HELP");
        quit = new JButton("QUIT");
        
        play.setActionCommand("go to player creator");
        savedGames.setActionCommand("load a saved game");
        help.setActionCommand("open the help menu");
        quit.setActionCommand("quit the game");
        
        play.setPreferredSize(buttons);
        savedGames.setPreferredSize(buttons);
        help.setPreferredSize(buttons);
        quit.setPreferredSize(buttons);
        
        play.addActionListener(ac);
        savedGames.addActionListener(ac);
        help.addActionListener(ac);
        quit.addActionListener(ac);
        

    }
    
    private void createLayout(){
        GroupLayout layout = new GroupLayout(this);
        
        setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(GroupLayout.Alignment.CENTER, 
                        layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                    .addComponent(play, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(savedGames, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(help, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(quit, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
                                        
                                        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(play, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(savedGames, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(help, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(quit, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }
    
}
