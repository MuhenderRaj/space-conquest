/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import com.muhender.spaceconquest.listeners.ButtonEventManager;
import com.muhender.spaceconquest.serializables.PlayerStats;
import com.muhender.spaceconquest.utilities.GameManager;
import com.muhender.spaceconquest.utilities.SceneManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author R Muhender Raj
 */
public class LoadGame extends JPanel{
    public JButton play, mainMenu, quit;
    private final int buttonWidth, buttonHeight;
    private ActionListener ac;
    PlayerStats stats;
    JLabel names[];
    JPanel namesHolder;
    JScrollPane namesHolderScroll;

    
    public LoadGame(){
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
        g2D.setFont(Constants.textFont);
                    
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds("Load Game", g2D);
        
        g2D.setColor(Color.WHITE);
        g2D.drawString("Load Game", this.getWidth() / 2 - (int)(fontRect.getWidth() / 2), 200 + (int)(fontRect.getHeight() / 2));
        
    }
    
    public void loadPlayer(String name){
        try {
            stats = GameManager.loadSaveGame(name);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Constants.level = stats.getLevel();
        Constants.maxHealth = stats.getMaxHealth();
        Constants.XP = stats.getXP();
        Constants.name = stats.getName();
        SceneManager.loadScene("Game Menu");
    }
    
    private void init(){
        String namesList[] = GameManager.getSaveGameNames();
        
        
        namesHolder = new JPanel();
        namesHolder.setBackground(Color.BLACK);
        namesHolder.setLayout(new GridLayout(0, 1));
        
        namesHolderScroll = new JScrollPane(namesHolder);
        namesHolderScroll.setBounds(this.getWidth() / 5, (int)(0.28 * this.getHeight()), this.getWidth() - 2 * this.getWidth() / 5, this.getHeight() / 3);
        namesHolderScroll.getVerticalScrollBar().setUnitIncrement(16);
        namesHolderScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        namesHolderScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        names = new JLabel[namesList.length];

        for(int i = 0; i < names.length; i++){
            names[i] = new JLabel(namesList[i]);
            names[i].setName(namesList[i]);
            names[i].setText(namesList[i]);
            names[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadPlayer(((Component)e.getSource()).getName());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ((Component)e.getSource()).setBackground(Color.RED);
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    ((Component)e.getSource()).setBackground(Color.BLACK);
                }
            });
            
            names[i].setFont(Constants.textFont);
            names[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            names[i].setForeground(Color.WHITE);
            names[i].setOpaque(true);
            names[i].setBackground(Color.BLACK);
            
            names[i].setPreferredSize(new Dimension(3* this.getWidth() / 5, 50));
            namesHolder.add(names[i]);
        }

        add(namesHolderScroll);
        
        ac = new ButtonEventManager();
        
        play = new JButton("PLAY");
        mainMenu = new JButton("MAIN MENU");
        quit = new JButton("QUIT");
        
        play.setActionCommand("start the game");
        mainMenu.setActionCommand("go to the main menu");
        quit.setActionCommand("quit the game");

        play.setBounds((this.getWidth() / 2 - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        mainMenu.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        quit.setBounds((3 * this.getWidth() / 2 - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
        
        play.addActionListener(ac);
        mainMenu.addActionListener(ac);
        quit.addActionListener(ac);
        
        add(play);
        add(mainMenu);
        add(quit);
    }
}
