/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import com.muhender.spaceconquest.main.developer.LevelEditor;
import com.muhender.spaceconquest.utilities.GameManager;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author R Muhender Raj
 */
public class Game extends JFrame{
    
    public static Game game;
    private final boolean developer;
    
    Game(){ //TODO mix gameplays of spacegame and match3. Match3 to collect resources, then use resources to spawn ships
        developer = false;
        loadFrame();
    }
    
    private void loadFrame(){
        setLayout(null);
        setTitle("Space Conquest");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SpritePlaceholder.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        if(!developer){
            
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
            setVisible(true);
            setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
            Constants.WIDTH = this.getWidth() / 2;
            Constants.HEIGHT = this.getHeight() / 2;
        
            Constants.BOARD_X = Constants.WIDTH;
            Constants.BOARD_WIDTH = Constants.WIDTH;
            setPanel(new MainMenu());
        }
        else{
            setVisible(true);
            setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            setLocationRelativeTo(null);
            setPanel(new LevelEditor());
        }
        
        pack();
    }
    
    /**
     * Use this to change scenes
     * @param panel The panel to change to 
     */
    public void setPanel(JPanel panel){
        getContentPane().removeAll();
        setContentPane(panel);
        revalidate();
        pack();
    }
    /**
     * Use this to load a scene with a loading screen in between
     * @param panel
     * @param withLoadingScreen set this to <code>true</code> to use this version
     * @see LoadingScreen
     */
    public void setPanel(JPanel panel, boolean withLoadingScreen){
        if(withLoadingScreen)
            setPanel(new LoadingScreen(panel));
        else
            setPanel(panel); 
    }
    
    public static void main(String... args){ 
        EventQueue.invokeLater(() -> {
            try {
                Constants.levels = GameManager.getPrefs().getLevels();
                game = new Game();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
