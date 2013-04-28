package edu.csn.mess.cit230.asteroids;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ray Scott
 * @version 3-20-2013
 */
public class Asteroid {
    
    private int    hitsLeft;   // To hold how many hits are left
    private int    numPieces;   // The number of smaller asteroids that are formed
    private double x;          // to hold the x value
    private double y;          // to hold the y value
    private double velocityX;  // to hold the xVelocity
    private double velocityY;  // to hold the yVelocity
    private double radius;     // the radius of the asteroid
    
    
    /**
     * Seven arguement constructor for the asteroid 
     */
    public Asteroid( double x, 
                     double y, 
                     double maxVel,
                     double minVel,
                     double radius) {
                     //int    hitsLeft,
                     //int    numPieces ) {
        
        this.x = x;  
        this.y = y;
        this.radius    = radius;
        //this.hitsLeft  = hitsLeft;
        //this.numPieces = numPieces;
        
        // sets a random velocity and direction for the asteroids
        double velocity = minVel + Math.random() * ( maxVel - minVel ),
            direction = 2 * Math.PI * Math.random();
        
        velocityX = velocity * Math.cos( direction ); 
        velocityY = velocity * Math.sin( direction );
    }

    /**
     * gets hits left
     * @return hitsLeft
     */
    public int getHitsLeft() {
        return hitsLeft;
    }

    /**
     * Gets number of pieces
     * @return numPieces
     */
    public int getNumPieces() {
        return numPieces;
    }
    
    /**
     * makes the asteroids move and reappear if they leave the screen
     * @param scrnWidth
     * @param scrnHeight 
     */
    public void move( int scrnWidth, int scrnHeight ) {
        
        // move the asteroid
        x += velocityX;
        y += velocityY;
        
        // makes the asteroid reappear on the opposite side of the window if it 
        // moves off the screen.
        if ( x < 0 - radius ) {
            x += scrnWidth + 2 * radius;
        }
        else if ( x > scrnWidth + radius ) {
            x -= scrnWidth + 2 * radius;
        }
        if ( y < 0 - radius ) {
            y+=scrnHeight+2*radius;
        }
        else if ( y > scrnHeight + radius ) {
            y -= scrnHeight + 2 * radius;
        }
    }
    
    public void draw( Graphics g ) {
        
        g.setColor(Color.GRAY);
        g.fillOval(( int) (x - radius + .5), (int) (y - radius + .5),
                (int) (2 * radius), (int) (2 * radius));
    }
    /**
     * collision detects if the asteroid has collided with the ship
     * @param ship
     * @return true or false
     */
    public boolean collision( Ship ship ) {
        
        if( Math.pow( radius + ship.getRadius(), 2 ) > Math.pow( ship.getX() - x, 2 ) + 
                Math.pow( ship.getY() - y, 2 ) && ship.isActive()) {
            
            return true;
        } 
        else {
            
            return false;
        }
    }
    
    /**
     * bulletHit checks to see if a bullet hits an asteroid
     * @param bullet
     * @returns true or false
     */
    public boolean bulletHit( Bullets bullet ) {
        
        if( Math.pow( radius, 2 ) > Math.pow( bullet.getX() - x, 2 ) +
                Math.pow( bullet.getY() - y, 2 )) {
            
            return true;
        }
        else {
            
            return false;
        }
    }
    
    
}
