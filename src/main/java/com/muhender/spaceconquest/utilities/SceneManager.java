/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.utilities;

import com.muhender.spaceconquest.main.*;
import com.muhender.spaceconquest.main.gamescreen.GamePanel;

/**
 * This is used to change scenes
 * @author R Muhender Raj
 */
public class SceneManager {
    
    /**
     * Use this method to change scenes to the scene specified in <code>Constants.currentScene</code>
     * 
     * @param scene the scene to change to
     * <br><br>
     * Gameplay<br>
     * Game Over<br>
     * Main Menu<br>
     * Game Menu<br>
     * Upgrades<br>
     * EnemyInfo<br>
     * @see Constants
     */
    public static void loadScene(String scene){
        
        switch(scene){
            case "Gameplay":
                Game.game.setPanel(new GamePanel(), true);
                break;
                
            case "Create Player":
                Game.game.setPanel(new CreatePlayer());
                break;
                
            case "Load Game":
                Game.game.setPanel(new LoadGame());
                break;
                
            case "Game Over":
                Game.game.setPanel(new GameOver());
                break;             
            
            case "Main Menu":
                Game.game.setPanel(new MainMenu());
                break;
            
            case "Game Menu":
                Game.game.setPanel(new GameMenu());
                break;
            
            case "Upgrades":
                Game.game.setPanel(new Upgrades());
                break;
            
            case "Enemy Info":
                Game.game.setPanel(new EnemyInfo());
                break;
            
            case "Help Menu":
                Game.game.setPanel(new HelpMenu());
                break;
                
            case "Win":
                Game.game.setPanel(new WinScreen());
                break;
                
            default:
                Game.game.setPanel(new GenericScreenForCopyConvenience());
                break;
        }
    }
    
    /**
     * This extends the <code>loadScene(String scene)</code> method by adding an optional message along with it.
     * @param scene
     * @param message 
     * @see loadScene(String scene)
     */
    public static void loadScene(String scene, String message){
        loadScene(scene);    
    }   
    
}
