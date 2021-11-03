/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import com.muhender.spaceconquest.listeners.ButtonEventManager;
import static com.muhender.spaceconquest.main.Constants.buttons;
import static com.muhender.spaceconquest.main.Constants.textFont;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 *
 * @author R Muhender Raj
 */
public class WinScreen extends JPanel{
    public JButton replay, next, menu;
    public JLabel titleText;
    private ActionListener ac;
    
    public WinScreen(){
        super();
        Constants.level++;
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
        setSize(Game.game.getSize());
        init();
        createLayout();
    }  
    
    protected void init(){

        
        //TODO do initialisation of objects here
        ac = new ButtonEventManager();
        
        titleText = new JLabel("You Win!");
        titleText.setFont(textFont);
        titleText.setForeground(Color.WHITE);
        
        replay = new JButton("REPLAY");
        next = new JButton("NEXT");
        menu = new JButton("MENU");
        
        replay.setActionCommand("replay the game");
        next.setActionCommand("go to the next level");
        menu.setActionCommand("go to game menu");
        
        titleText.setPreferredSize(buttons);
        replay.setPreferredSize(buttons);
        next.setPreferredSize(buttons);
        menu.setPreferredSize(buttons);
        
        replay.addActionListener(ac);
        next.addActionListener(ac);
        menu.addActionListener(ac);
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
                                    .addComponent(titleText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(replay, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(next, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                                    .addComponent(menu, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
                                        
                                        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(replay, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(next, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE).addGap(40)
                    .addComponent(menu, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }
    
}
