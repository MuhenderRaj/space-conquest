/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhender.spaceconquest.math;

import java.awt.Point;


/**
 * A class for the mathematical construction of a vector
 * @author R Muhender Raj
 */
public class Vector2 {    
    private double x, y;
    
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    } 
    
    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    } 
    
    public Vector2(Vector2 v){
        x = v.getX();
        y = v.getY();
    }
    
    /**
     * Scales the vector by a factor
     * @param factor the factor by which the vector is to be scaled 
     * @return the scaled vector as a new instance
     */
    public Vector2 scale(double factor){
        return new Vector2(x * factor, y * factor);
    }
    
    /**
     * Adds two vectors
     * @param v the vector to be added to the current vector 
     * @return the resultant vector instance
     */
    public Vector2 add(Vector2 v){
        x += v.getX();
        y += v.getY();
        return this;
    }
    
    public Vector2 addAndNew(Vector2 v){
        return new Vector2(v.getX() + x, v.getY() + y);
    }
    
    /**
     * Calculates the scalar dot product of two vectors
     * @param v the vector to be dotted with this vector
     * @return the scalar dot product of the two vectors 
     */
    public double dotProduct(Vector2 v) {
        return (x * v.getX() + y * v.getY());
    }
    
    public double magnitude(){
        return Math.sqrt((x * x) + (y * y));
    }
    
    public Vector2 direction(){
        return new Vector2(this.scale(1 / this.magnitude()));
    }
    
    public static Vector2 position(Point p){
        return new Vector2(p.getX(), p.getY());
    }
    
    /**
     * @return the x component of the vector
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y component of the vector
     */
    public double getY() {
        return y;
    }

    /**
     * @param x set the x component of the vector
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y set the y component of the vector
     */
    public void setY(double y) {
        this.y = y;
    }
    
    
    @Override
    public String toString(){
        return "Vector v("+this.hashCode()+") = " + x + "i + " + y + "j";
    }

    
    /**
     * Performs a linear interpolation of two vectors
     * @param init the initial vector
     * @param fin the final vector
     * @param key
     * @return the interpolated vector
     */
    public static Vector2 lerp(Vector2 init, Vector2 fin, double key){
        if(key > 1)
            return fin;
        
        return init.scale(1 - key).add(fin.scale(key));
    }
    
}
