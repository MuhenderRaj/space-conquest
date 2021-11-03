/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.listeners;

import com.muhender.spaceconquest.utilities.SceneManager;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.CreatePlayer;
import com.muhender.spaceconquest.main.developer.LevelEditor;
import com.muhender.spaceconquest.utilities.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * This manages button click events
 * @author R Muhender Raj
 */
public class ButtonEventManager implements ActionListener {
    
    JPanel l;
    
    public ButtonEventManager(){
    
    }
    
    public ButtonEventManager(JPanel l){
        this.l = l;
    }
    
    @Override
    public void actionPerformed(ActionEvent a) {
        
        
        
        switch(a.getActionCommand()){
            case "start the game":
                SceneManager.loadScene("Gameplay");
                break;
            
            case "open the help menu":
                SceneManager.loadScene("Help Menu");
                break;
                
            case "quit the game":
                System.exit(0);
                break;
            
            case "save and quit the game":           
                try {
                    GameManager.setPrefs();
                    GameManager.createSaveGame();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonEventManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                System.exit(0);
                break;

            
            case "replay the game":
                Constants.resetStats();
                SceneManager.loadScene("Play");
                break;
            
            case "go to the main menu":
                SceneManager.loadScene("Main Menu");
                break;
                
            case "save and go to the main menu":
                try {
                    GameManager.setPrefs();
                    GameManager.createSaveGame();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonEventManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SceneManager.loadScene("Main Menu");
                break;
                
            case "resume the game":
                Constants.isPaused = false;
                break;
            case "go to game menu":
                Constants.isPaused = false;
                SceneManager.loadScene("Game Menu");
                break;
                
            case "go to game menu after saving":
                if(l instanceof CreatePlayer)
                    ((CreatePlayer)l).getPlayerName();
                try {
                    GameManager.createSaveGame();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonEventManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                SceneManager.loadScene("Game Menu");
                break;

                
            case "restart the level":
                Constants.isPaused = false;
                SceneManager.loadScene("Play");
                break;
            case "see the enemy info":
                SceneManager.loadScene("Enemy Info");
                break;
            case "go to upgrades":
                SceneManager.loadScene("Upgrades");
                break;
            case "add the level":
                if(l instanceof LevelEditor)
                    ((LevelEditor)l).getNumbers();
                break;
                
            case "rewrite a level":
                if(l instanceof LevelEditor)
                    ((LevelEditor)l).getLevel();
                break;
                
            case "go to the next level":
                SceneManager.loadScene("Play");
                break;
                
            case "go to player creator":
                SceneManager.loadScene("Create Player");
                break;
                
            case "load a saved game":
                SceneManager.loadScene("Load Game");
                break;
                
            default:
                SceneManager.loadScene("Default");
                break;
        }        
    }
}
