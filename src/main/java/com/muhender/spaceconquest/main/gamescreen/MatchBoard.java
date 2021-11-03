/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main.gamescreen;

import com.muhender.spaceconquest.listeners.MouseManager;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.Game;
import com.muhender.spaceconquest.main.LoadingScreen;
import com.muhender.spaceconquest.sprites.Gem;
import com.muhender.spaceconquest.sprites.GemColor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author R Muhender Raj
 */
public class MatchBoard extends JPanel implements Runnable{
    private Thread thread;
    private Gem board[][], temp;
    private int location[][] = new int[2][2];
    private final int width = 8, height = 8;
    private Random r;
    private MouseManager m;
    private boolean updatable = true;
    
    public MatchBoard(){
        super();
        board = new Gem[width][height];
        location[0][0] = -1;
        
        initialize();
        if(thread != null)
            thread.interrupt();
        thread = new Thread(this, "MatchBoard");
        thread.start();
        
    }

    private void initialize(){

        setBackground(Color.YELLOW);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(GamePanel.board.getSize());
        
        r = new Random(System.currentTimeMillis());
        
        GemColor tempColor;
        m = new MouseManager(this);
        
        addMouseListener(m);
        addMouseMotionListener(m);
        
        
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++){  
                tempColor = GemColor.values()[r.nextInt(GemColor.values().length)];
                while(matchesExist(i, j, tempColor)){
                    tempColor = GemColor.values()[r.nextInt(GemColor.values().length)];
                }
                board[i][j] = new Gem(tempColor);  
            }
          

    }
    
    private boolean matchesExist(int i, int j, GemColor temp){
        return (isValidCell(i, j - 2) && board[i][j - 1].color == board[i][j - 2].color && board[i][j - 1].color == temp)
                || (isValidCell(i - 2, j) && board[i - 1][j].color == board[i - 2][j].color && board[i - 1][j].color == temp);
    }
    
    private boolean isValidCell(int x, int y){
        return x >= 0 && y >= 0 && x < height && y < width;
    }
    
    private void update(){
        //animate();
        
        if(updatable){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    board[i][j].updatePosition(i * Gem.HEIGHT, j * Gem.WIDTH);
                }
            }
            System.out.println("Updated");
            updatable = false;
            //checkForMatches();
            //destroyMatches();
            //updateBoard();
        }
    }
    
    
    
    private boolean checkForPlayerMatches(){
        
        int counter = 0;
        int rowOfFirst = location[1][0], columnOfFirst = location[1][1], rowOfSecond = location[0][0], columnOfSecond = location[0][1];
        GemColor firstColor = board[rowOfFirst][columnOfFirst].color;
        GemColor secondColor = board[rowOfSecond][columnOfSecond].color;
        boolean foundMatch = true; //set to false
        
        //horizontalScan
        
        
        //verticalScan
        
        
        
        return foundMatch;
    }
    
    private List<Gem> matchList(GemColor color, int row, int col){
        List<Gem> list = new LinkedList<>();
        int count = 1;
        
        //if()
        
        return list;
    }
    
    public void pressed(Point p){
        if(location[0][0] == -1){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    if(board[i][j].getCollider().contains(p)){
                        location[0][0] = i;
                        location[0][1] = j;
                        break;
                    }
                }
            }
        }
        if(location[0][0] == -1)
            m.hasInMemory = false;
    }
    public void released(Point p){
        if(location[0][0] != -1){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    if(board[i][j].getCollider().contains(p)){
                        //do something to ensure only adjacents swap
                        location[1][0] = i;
                        location[1][1] = j;
                    }
                }
            }
        } 
        swapGems();
    }
    
    public void swapGems(){
        temp = board[location[0][0]][location[0][1]];
        board[location[0][0]][location[0][1]] = board[location[1][0]][location[1][1]];
        board[location[1][0]][location[1][1]] = temp;
        if(checkForPlayerMatches()){
            try{
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(MatchBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            location = new int[2][2];
            location[0][0] = -1;
            updatable = true;
            System.out.println("Swapped");
        }
        else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MatchBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            temp = board[location[0][0]][location[0][1]];
            board[location[0][0]][location[0][1]] = board[location[1][0]][location[1][1]];
            board[location[1][0]][location[1][1]] = temp;
            updatable = false;
            //System.out.println("Invalid");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);   //vvv Important. Don't forget this. This paints the bg color
        for(int row = 0; row < height; row++)
            for(int column = 0; column < width; column++)
                if(board[row][column] != null){
                    board[row][column].drawSprite(g2D, this);
                }
        //        
    }
    
    @Override
    public void run() {
        long startTime;
        double TimePerFrame;
        long sleepTime;
        
        repaint();
        do{
            if(Constants.isPaused){
                try {
                    PauseDialog.thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            startTime = System.nanoTime();
            update(); 
            //requestFocus();
            TimePerFrame = (System.nanoTime() - startTime)/1000000;
            sleepTime = (long)(Constants.deltaTime - TimePerFrame);
            if(sleepTime < 0){ 
                System.out.println("Overworked");
                sleepTime = 5;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(MatchBoard.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //if(true)
                //requestFocus();
            repaint();
        } while(Game.game.getContentPane() instanceof GamePanel || Game.game.getContentPane() instanceof LoadingScreen);
    }
}
