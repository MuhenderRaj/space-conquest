/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template levelWriter, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.utilities;

import com.muhender.spaceconquest.main.Constants;
import com.muhender.spaceconquest.serializables.GamePrefs;
import com.muhender.spaceconquest.serializables.LevelData;
import com.muhender.spaceconquest.serializables.PlayerStats;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author R Muhender Raj
 */
public class GameManager {
    private static FileOutputStream levelWriter, prefWriter, playerWriter;
    private static ObjectOutputStream levelObjectWriter, prefObjectWriter, playerObjectWriter;
    private static FileInputStream levelReader, prefReader, playerReader;
    private static ObjectInputStream levelObjectReader, prefObjectReader, playerObjectReader;
    private static File saveGames[];
    
    public static GamePrefs getPrefs() throws IOException, ClassNotFoundException{
        prefReader = new FileInputStream("src/main/java/serialized/gamePrefs/gamedata.ser");
        prefObjectReader = new ObjectInputStream(prefReader);
        GamePrefs prefs = (GamePrefs)prefObjectReader.readObject();
        prefObjectReader.close();
        return prefs;
    }
    
    public static void setPrefs() throws IOException{
        prefWriter = new FileOutputStream("src/main/java/serialized/gamePrefs/gamedata.ser");
        prefObjectWriter = new ObjectOutputStream(prefWriter);
        prefObjectWriter.writeObject(new GamePrefs(Constants.levels));
        prefObjectWriter.flush();
        prefObjectWriter.close();
    }

    public static void createSaveGame() throws IOException {
        playerWriter = new FileOutputStream("src/main/java/serialized/savedgames/" + Constants.name.toLowerCase() + "sav.ser");
        playerObjectWriter = new ObjectOutputStream(playerWriter);
        playerObjectWriter.writeObject(new PlayerStats(Constants.name, Constants.XP, Constants.level, Constants.maxHealth));
        playerObjectWriter.flush();
        playerObjectWriter.close();
    }
    
    public static String[] getSaveGameNames(){
        saveGames = new File("src/main/java/serialized/savedgames").listFiles();
        String names[] = new String[saveGames.length];
        int i = 0;
        for(File save : saveGames){
            names[i++] = save.getName();
            names[i - 1] = names[i - 1].substring(0, names[i - 1].length() - 7);
        }
        return names;
    }
    
    public static PlayerStats loadSaveGame(String name) throws IOException, ClassNotFoundException{
        playerReader = new FileInputStream("src/main/java/serialized/savedgames/" + name + "sav.ser");
        playerObjectReader = new ObjectInputStream(playerReader);
        PlayerStats stats = (PlayerStats)playerObjectReader.readObject();
        playerObjectReader.close();
        return stats;
    }
    
    public static void createLevel(int... data) throws IOException{
        levelWriter = new FileOutputStream("src/main/java/serialized/levels/level" + ++Constants.levels + ".ser");
        levelObjectWriter = new ObjectOutputStream(levelWriter);
        levelObjectWriter.writeObject(new LevelData(data[0], data[1], data[2], data[3]));
        levelObjectWriter.flush();
        levelObjectWriter.close();
    }
    
    public static LevelData loadLevel(int level) throws IOException, ClassNotFoundException{
        levelReader = new FileInputStream("src/main/java/serialized/levels/level" + level + ".ser");
        levelObjectReader = new ObjectInputStream(levelReader);
        LevelData data = (LevelData)levelObjectReader.readObject();
        levelObjectReader.close();
        return data;
    }
    
    public static void rewriteLevel(int level, int... data) throws IOException{
        levelWriter = new FileOutputStream("src/main/java/serialized/levels/level" + level + ".ser");
        levelObjectWriter = new ObjectOutputStream(levelWriter);
        levelObjectWriter.writeObject(new LevelData(data[0], data[1], data[2], data[3]));
        levelObjectWriter.flush();
        levelObjectWriter.close();
    }
}
