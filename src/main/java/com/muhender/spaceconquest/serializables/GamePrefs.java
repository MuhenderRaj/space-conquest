/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.serializables;

import java.io.Serializable;

/**
 * JavaBean used to save a game and load it afterwards
 * @author R Muhender Raj
 */
public class GamePrefs implements Serializable{
    private static final long serialVersionUID = 92L;

    private final int levels;
    
    public GamePrefs(int levels) {
        this.levels = levels;
    }

    public int getLevels() {
        return levels;
    }
}
