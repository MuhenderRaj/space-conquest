/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.serializables;

import java.io.Serializable;

/**
 *
 * @author R Muhender Raj
 */
public class LevelData implements Serializable{
    private static final long serialVersionUID = 9L;
    private final int enemyNumbers[] = new int[4];
    
    public LevelData(int basic, int seeking, int shooting, int shielded){
        enemyNumbers[0] = basic;
        enemyNumbers[1] = seeking;
        enemyNumbers[2] = shooting;
        enemyNumbers[3] = shielded;
    }
    
    public int[] getEnemies(){
        return enemyNumbers;
    }
}
