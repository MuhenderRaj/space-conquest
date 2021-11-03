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
public class PlayerStats implements Serializable{

    private static final long serialVersionUID = 169L;
    
    private String name;
    private long XP;
    private int level;
    private int maxHealth;

    public PlayerStats(String name, long XP, int level, int maxHealth) {
        this.name = name;
        this.XP = XP;
        this.level = level;
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getXP() {
        return XP;
    }

    public void setXP(long XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }


    
}
