/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.main;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

/**
 * This class stores all the static constants (and variables) needed in the game
 * @author R Muhender Raj
 */
public class Constants {
    public static int WIDTH = 1080, HEIGHT = 720;
    public static int BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT;
    
    public static Dimension buttons = new Dimension(200, 60);
    public static Font textFont = new Font("Arial", 30, 30);
    
    public static final int FPS = 60;

    /**
     * The time duration(in milliseconds) of a frame
     */
    public static final double deltaTime = 1000 / FPS;
    
    public static int levels = 0;    
    
    public static boolean isPaused = false;
    
    public static double shootTimeout = 0;
    public static final double SHOOT_TIMEOUT = 0.25;

    public static String name = null;
    public static long XP = 0;
    public static int level = 1;
    public static int maxHealth = 100;
    
    public static final double PLAYER_SPEED = 250;
    public static int shootLevel = 1;
    public static long shooterTimeDelay = 3000;
    
    public static Random random = new Random(System.currentTimeMillis());
    
    public static void resetStats(){
        shootLevel = 1;
        //Reset all player accomplishments here
    }
}
