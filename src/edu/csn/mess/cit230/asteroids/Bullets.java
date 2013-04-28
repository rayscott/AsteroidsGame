package edu.csn.mess.cit230.asteroids;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ray Scott
 * @version 3-20-2013
 */
public class Bullets {
    
    private final double bulletSpeed = 10;  // the speed which the bullet moves
    private int    bulletDie;  // the value which the bullet no loger exists
    private double x;          // to hold the x value
    private double y;          // to hold the y value
    private double xVelocity;  // to hold the xVelocity value
    private double yVelocity;  // to jold the yVelocity value
    
    
    public Bullets( int bulletDie, 
            double x, 
            double y,
            double angle,
            double shipXVelocity,
            double shipYVelocity ) {
        
        this.x = x;
        this.y = y;
        this.bulletDie = bulletDie;
        
        // make sure the bullet speed is increased if the ship is moving 
        xVelocity = bulletSpeed * Math.cos ( angle ) + shipXVelocity; 
        yVelocity = bulletSpeed * Math.sin ( angle ) + shipYVelocity;
    }

    /**
     * gets bulletDie
     * @return bulletDie
     */
    public int getBulletDie() {
        return bulletDie;
    }

    /**
     * gets x
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * gets y
     * @return y
     */
    public double getY() {
        return y;
    }
    
    /**
     * Draw the bullets 
     * @param g
     */
    public void draw( Graphics g ) {
        
        // make bullets red
        g.setColor( Color.red );
        
        // draw the bullets and fill
        g.fillOval(( int )( x - .5 ), ( int )( y - .5 ), 3, 3);
    }
    
    /**
     * Moves the bullet from the ship
     * @param scrnWidth
     * @param scrnHeight
     */
    public void move( int scrnWidth, int scrnHeight ) {
        
        // make bullets move
        x += xVelocity;
        y += yVelocity;
        
        // if bullet goes too long without hitting anything
        bulletDie--;
        
    }
}
