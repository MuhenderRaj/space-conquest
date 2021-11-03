/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.listeners;

import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.Game;
import com.muhender.spaceconquest.main.gamescreen.GameField;
import com.muhender.spaceconquest.main.gamescreen.GamePanel;
import com.muhender.spaceconquest.main.gamescreen.PauseDialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * This manages key events
 * @author R Muhender Raj
 */
public class KeyEventManager implements KeyListener{
    
    private GameField panel;
    private GamePanel p;
    private Set<Integer> pressedKeys = new HashSet<>();
    
    public KeyEventManager(GameField panel){
        this.panel = panel;
    }
    
    public KeyEventManager(){
    }
    
    @Override
    public void keyPressed(KeyEvent k){
        pressedKeys.add(k.getKeyCode());
        
        for(Integer key: pressedKeys){
//            if(key == KeyEvent.VK_SPACE && Constants.shootTimeout <= 0 && !Constants.isPaused){
//                panel.instantiatePulse();
//                Constants.shootTimeout = Constants.SHOOT_TIMEOUT;
//            }
//            else{
                switch (key) {
                    case KeyEvent.VK_C:                            //remove this in final game, used to cheat in development process
                        System.out.println("Upgraded!");
                        Constants.shootLevel++;
                        break;
                        
                    case KeyEvent.VK_P:
                        if(!Constants.isPaused)
                            Game.game.getContentPane().add(new PauseDialog());
                        Constants.isPaused = !Constants.isPaused;
                        break;
                        
                    case KeyEvent.VK_UP:
                        GameField.player.getVelocity().setY(- Constants.PLAYER_SPEED);
                        if(GameField.player.getCurrentAnimation() != 2)
                            GameField.player.changeAnimation(2);
                        break;
                        
                    case KeyEvent.VK_DOWN:
                        GameField.player.getVelocity().setY(Constants.PLAYER_SPEED);
                        if(GameField.player.getCurrentAnimation() != 3)
                            GameField.player.changeAnimation(3);
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                        GameField.player.getVelocity().setX(Constants.PLAYER_SPEED);
                        break;
                        
                    case KeyEvent.VK_LEFT:
                        GameField.player.getVelocity().setX(- Constants.PLAYER_SPEED);
                        break;
                        
                    default:
                        break;
                }
//            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent k){
        pressedKeys.remove(k.getKeyCode());
        switch (k.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                GameField.player.getVelocity().setY(0);
                GameField.player.changeAnimation(0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                GameField.player.getVelocity().setX(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }
}
