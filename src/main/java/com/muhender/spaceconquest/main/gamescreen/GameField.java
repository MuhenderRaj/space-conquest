/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main.gamescreen;

import com.muhender.spaceconquest.listeners.*;
import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.main.Game;
import com.muhender.spaceconquest.main.LoadingScreen;
import com.muhender.spaceconquest.math.Vector2;
import com.muhender.spaceconquest.serializables.LevelData;
import com.muhender.spaceconquest.sprites.*;
import com.muhender.spaceconquest.sprites.enemies.*;
import com.muhender.spaceconquest.sprites.pulses.*;
import com.muhender.spaceconquest.utilities.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author R Muhender Raj
 */
public class GameField extends JPanel implements Runnable{ 

    private final int buttonHeight;
    private final int buttonWidth;
    private Thread thread;
    private Random r;
    public static Player player;
    double updateCounter = 1;
    private List<PlayerPulse> playerPulses;
    private List<EnemyPulse> enemyPulses;
    private List<Enemy> enemies;
    private KeyListener k;
    private MouseListener m;
    private MouseMotionListener mm;
    ActionListener b;
    public boolean laserActive;
    private Vector2 laserFinalPosition;
    JButton resume, saveQuit, restart;
    LevelData levelData;
    
    public final int level;
    
    private int basic, seeking, shooting, shielded;
    private final float basicProb = 0.4f, seekingProb = 0.3f, shootingProb = 0.2f, shieldedProb = 0.1f; //modifiable
    private float laneWidth;
    private boolean noneLeft = false;
    
    public GameField(){

        super();
        this.level = Constants.level;
        buttonWidth = 200;
        buttonHeight = 60;
        
        initialize();
        
        if(thread != null)
            thread.interrupt();
        thread = new Thread(this, "Play level " + level);
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        
        if(player!=null)
            player.drawSprite(g2D, this); 
        if(playerPulses != null)
            for(Pulse p: playerPulses)
                p.drawSprite(g2D, this);
        if(enemyPulses != null)
            for(Pulse p: enemyPulses)
                p.drawSprite(g2D, this);
        if(enemies != null)
            for(Enemy e: enemies){
                e.drawSprite(g2D, this);
            }
        if(laserActive){
            g2D.drawLine((int)player.getPosition().getX() + Player.WIDTH, (int)player.getPosition().getY() + Player.HEIGHT / 2, (int)laserFinalPosition.getX(), (int)laserFinalPosition.getY());
        }
        
        g2D.setColor(Color.WHITE);
        
        for(int i = 0; i <= 5; i++)
            g2D.drawLine(0, i * (int)laneWidth, this.getWidth(), i * (int)laneWidth);
        
        //Brings up the pause menu
//        if(Constants.isPaused){
//            g2D.setColor(new Color(200,200,200));
//            int boxWidth = (int)(0.4 * this.getWidth());
//            int boxHeight = (int)(0.8 * this.getHeight());
//            g2D.fillRect((this.getWidth() - boxWidth) / 2, (this.getHeight() - boxHeight) / 2, boxWidth, boxHeight);
//        }
        
    }
    private void update(){
//        if(!Constants.isPaused){
//            if(resume.isVisible())
//                resume.setVisible(false);
//            if(saveQuit.isVisible())
//                saveQuit.setVisible(false);
//            if(restart.isVisible())
//                restart.setVisible(false);
            
            if(player.getHealth() <= 0){
                SceneManager.loadScene("Game Over");
                return;
            }
        
            updatePositions();
            checkForCollisions();
            
            updateCounter -= Constants.deltaTime / 1000;
            if(updateCounter <= 0){
                spawnEnemies();
                updateCounter = 1;
            }
            if(basic == 0 && seeking == 0 && shooting == 0 && shielded == 0)
                if(enemies.isEmpty())
                    SceneManager.loadScene("Win");
            

            Constants.shootTimeout -= Constants.deltaTime / 1000;
//        }
//        else{
//            if(!resume.isVisible())
//                resume.setVisible(true);
//            if(!saveQuit.isVisible())
//                saveQuit.setVisible(true);
//            if(!restart.isVisible())
//                restart.setVisible(true);
//        }
    }
    
    private void updatePositions(){
        if(player!=null)
            player.updatePosition(); 
        if(playerPulses != null)
            for(Pulse p: playerPulses)
                p.updatePosition();
        if(enemyPulses != null)
            for(Pulse p: enemyPulses)
                p.updatePosition();
        if(enemies != null)
            for(Enemy e: enemies)
                e.updatePosition();
    }
    
    private void checkForCollisions() {
        for(PlayerPulse p: playerPulses){
            if((p.getPosition().getX() > this.getWidth() + 50)){
                playerPulses.remove(p);
            }
            else
                for(Enemy e: enemies){
                    if(p.getCollider().intersects(e.getCollider())){
                        playerPulses.remove(p);
                        e.reduceHealth(p.getDamage());
                        if(e.getHealth() <= 0){
                            enemies.remove(e);
                            player.increaseXP(e.getXPIncrease());
                            Enemy.enemyCount--;
                        }
                    }
                    else if((e instanceof ShootingEnemy) && e.getPosition().getX() < this.getWidth() * 0.8){
                        e.setVelocity(new Vector2(0, 0));
                    }
                }
        }
        for(EnemyPulse p: enemyPulses){
            if((p.getPosition().getX() < 0)){
                enemyPulses.remove(p);
            }
            else if(p.getCollider().intersects(player.getCollider())){
                enemyPulses.remove(p);
                player.reduceHealth(p.getDamage());
            }
        }
        for(Enemy e: enemies){
        
            if(e.getCollider().intersects(player.getCollider())){
                enemies.remove(e);
                player.reduceHealth(e.getDamage());
                Enemy.enemyCount--;
            }
            else if(e.getPosition().getX() < -50){
                enemies.remove(e);
                Enemy.enemyCount--;
                
                player.reduceHealth(e.getDamage() / 4);                         //Harm the player by a lesser factor.
                
                if(e instanceof BasicEnemy) basic--;
                else if(e instanceof SeekingEnemy) seeking--;
                else if(e instanceof ShootingEnemy) shooting--;
                else if(e instanceof ShieldedEnemy) shielded--;
            }
        }
        
    }
    
    private void spawnEnemies(){       
        double randNum = r.nextDouble();
        if(randNum < basicProb && basic > 0){
            enemies.add(new BasicEnemy(new Vector2(this.getWidth() + BasicEnemy.WIDTH, (r.nextInt(5)) * laneWidth), new Vector2(-BasicEnemy.speed, 0)));
            basic--;
        }else if(randNum < basicProb + seekingProb && seeking > 0){
        //    enemies.add(new SeekingEnemy(new Vector2(this.getWidth() + SeekingEnemy.WIDTH, r.nextInt(this.getHeight() - SeekingEnemy.HEIGHT)), new Vector2(-SeekingEnemy.speed, 0), this));
            seeking--;
        }
        else if(randNum < basicProb + seekingProb + shootingProb && shooting > 0){
        //    enemies.add(new ShootingEnemy(new Vector2(this.getWidth() + ShootingEnemy.WIDTH, r.nextInt(this.getHeight() - ShootingEnemy.HEIGHT)), new Vector2(-ShootingEnemy.speed, 0), this));
            shooting--;
        }
        else{
            if(shielded > 0){
        //        enemies.add(new ShieldedEnemy(new Vector2(this.getWidth() + ShieldedEnemy.WIDTH, r.nextInt(this.getHeight() - ShieldedEnemy.HEIGHT)), new Vector2(-ShieldedEnemy.speed, 0), this)); 
                shielded--;
            }
        }
    }
    /**
     * Use this method to instantiate a pulse from the player
     */
    public void instantiatePulse(){
        switch(Constants.shootLevel){
            case 1:
                playerPulses.add(new PlayerPulse(new Vector2(player.getPosition()), new Vector2(PlayerPulse.speed, 0)));
                break;
            
            case 5:
            case 4:
            case 3:
                playerPulses.add(new PlayerPulse(new Vector2(player.getPosition()), new Vector2(PlayerPulse.speed, 0)));
            case 2:
                playerPulses.add(new PlayerPulse(new Vector2(player.getPosition()).add(new Vector2(0, -20)), new Vector2(PlayerPulse.speed, -PlayerPulse.speed / 20)));
                playerPulses.add(new PlayerPulse(new Vector2(player.getPosition()).add(new Vector2(0, 20)), new Vector2(PlayerPulse.speed, PlayerPulse.speed / 20)));
            break;
        }
    }
    
    public void instantiateEnemyPulse(Enemy enemy){
        if(enemy instanceof ShootingEnemy){
            enemyPulses.add(new EnemyPulse(new Vector2(enemy.getPosition()).add(new Vector2(-10, ShootingEnemy.HEIGHT / 2)), new Vector2(-EnemyPulse.speed, 0)));
        }
    }
    
    public void instantiateLaser(Vector2 position) {
        laserActive = true;
        laserFinalPosition = position;
    }
    
    private void initialize(){
        
        setBackground(Color.BLUE);
        setLayout(null);
        setFocusable(true);
        requestFocus();
        setSize(GamePanel.field.getSize());

        laneWidth = this.getHeight() / 5;
        laserActive = false;
        //setSize(this.getWidth(), this.getHeight());


        //buttons and stuff
//        resume = new JButton("RESUME");
//        saveQuit = new JButton("QUIT TO MENU");
//        restart = new JButton("RESTART LEVEL");
//        
//        resume.setActionCommand("resume the game");
//        saveQuit.setActionCommand("go to game menu");
//        restart.setActionCommand("restart the level");
//        
//        b = new ButtonEventManager();
//        
//        resume.addActionListener(b);
//        saveQuit.addActionListener(b);
//        restart.addActionListener(b);
//        
//        resume.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.4 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        saveQuit.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.55 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        restart.setBounds((this.getWidth() - buttonWidth) / 2, (int)(0.7 * this.getHeight() - buttonHeight / 2), buttonWidth, buttonHeight);
//        
//        resume.setVisible(false);
//        saveQuit.setVisible(false);
//        restart.setVisible(false);
//        
//        add(resume);
//        add(saveQuit);
//        add(restart);
        
        k = new KeyEventManager(this);
        addKeyListener(k);
        
        m = new MouseEventManager(this);
        addMouseListener(m);
        
        mm = new MouseMotionEventManager(this);
        addMouseMotionListener(mm);
        
        
        
        player = new Player(new Vector2(50, 300), new Vector2(0 , 0));
        playerPulses = new CopyOnWriteArrayList<>();
        enemies = new CopyOnWriteArrayList<>();
        r = new Random(System.currentTimeMillis());
        enemyPulses = new CopyOnWriteArrayList<>();
        
        try {
            levelData = GameManager.loadLevel(level);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        basic = levelData.getEnemies()[0];
        seeking = levelData.getEnemies()[1];
        shooting = levelData.getEnemies()[2];
        shielded = levelData.getEnemies()[3];
    }
    
    @Override
    public void run(){
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
                Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            repaint();
        } while(Game.game.getContentPane() instanceof GamePanel || Game.game.getContentPane() instanceof LoadingScreen);
    }
}


