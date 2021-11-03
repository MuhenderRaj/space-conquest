/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.muhender.spaceconquest.main.gamescreen;

import com.muhender.spaceconquest.listeners.KeyEventManager;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.Game;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 *
 * @author R Muhender Raj
 */
public class GamePanel extends JPanel implements Runnable{
    public GameField gameField;
    public MatchBoard matchBoard;
    public Things thingsList;
    public Description desc;
    public Header header;
    private Thread t;
    public static Rectangle field, board, things, description, head;
    public int headerSize, width, height;
    
    public GamePanel(){
        super();
        //setBackground(Color.BLACK);
        setLayout(null);
        //setSize(this.getWidth(), this.getHeight());
        
        setSize(Game.game.getSize());
        setFocusable(true);
        width = this.getWidth();
        height = this.getHeight();
        headerSize = height / 15;
        
        t = new Thread(this, "Gameplay");
        t.start();
        
    }
    
    private void initialize(){
        

        //try and move all listeners here      
        
        
        field = new Rectangle(0, headerSize, 2 * width / 3, height / 2);              
        board = new Rectangle(2 * width / 3, headerSize, width / 3, height / 2);
        things = new Rectangle(0, height / 2 + headerSize, width / 2, height / 2 - headerSize);
        description = new Rectangle(width / 2, height / 2 + headerSize, width / 2, height / 2 - headerSize);
        head = new Rectangle(0, 0, width, headerSize);
        
        gameField = new GameField();
        matchBoard = new MatchBoard();
        thingsList = new Things();
        desc = new Description();
        header = new Header();
        
        gameField.setBounds(field);
        matchBoard.setBounds(board);
        thingsList.setBounds(things);
        desc.setBounds(description);
        header.setBounds(head);
        
//        desc.setSize(description.getSize());
//        thingsList.setSize(things.getSize());
        
        //setComponentZOrder(matchBoard, 0);
        //setComponentZOrder(gameField, 1);
        
        
        
        add(gameField);
        add(matchBoard);
        add(thingsList);
        add(desc);
        add(header);
        
        Game.game.setPanel(this);
    }

    @Override
    public void run() {
        KeyListener k = new KeyEventManager();
        addKeyListener(k);
        long startTime;
        double timePerFrame;
        long sleepTime;        
        initialize();
        do{
            if(Constants.isPaused){
                try {
                    PauseDialog.thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            startTime = System.nanoTime();
            timePerFrame = (System.nanoTime() - startTime)/1000000;
            sleepTime = (long)(Constants.deltaTime - timePerFrame);
            if(sleepTime < 0){ 
                System.out.println("OverworkedPanel");
                sleepTime = 5;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } 
            requestFocus();
        }while(Game.game.getContentPane() instanceof GamePanel);
    }    
}
