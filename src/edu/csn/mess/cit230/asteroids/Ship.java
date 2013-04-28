package edu.csn.mess.cit230.asteroids;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ray Scott
 * @version 3-19-2013
 */
public class Ship {

    private final double[] origXPts = {14, -10, -6, -10}; // x coordinates for ship
    private final double[] origYPts = {0, -8, 0, 8};      // y coordinates for ship
    private final int radius        = 6;                         
    private double    x;              // for x value
    private double    y;              // for y value
    private double    angle;          // angle for turning
    private double    xVelocity;      // for movement of ships x-coordinates
    private double    yVelocity;      // for movement of ships y-coordinates
    private double    acceleration;   // for ships acceleration foreward
    private double    velocityDecay;  // for ships deceleration
    private double    rotation;       // ships rotation
    private boolean   turnLeft;       // to tells if ship is turning left
    private boolean   turnRight;      // to tells if ship if turning right
    private boolean   accelerate;     // to tell if ship is accelerating
    private boolean   active;         // to tell if the ship is active
    private int[]     xPts;           // to hold ships x coordinates
    private int[]     yPts;           // to hold ships y coordinates
    private int       bulletDelay;    // to hold the delay of the 
    private int       bulletsLeft;    // for haow many bullets are left
    
    // ship constructor that takes 7 arguements
    public Ship(double x, 
                double y, 
                double angle, 
                double accelerat, 
                double velocityDecay, 
                double rotation,
                int shotDelay) {
        
        this.y             = y;
        this.angle         = angle;
        this.accelerate    = accelerate;
        this.velocityDecay = velocityDecay;
        this.rotation      = rotation;
        this.bulletDelay   = bulletDelay; 
        
        xVelocity   = 0;          // ship not moving
        yVelocity   = 0;          // ship not moving
        turnLeft    = false;      // ship not turning
        turnRight   = false;      // ship not turning
        accelerate  = false;      // not accelerating
        active      = false;      // starts the game paused
        xPts        = new int[4]; // sets up array to hold 4 x points 
        yPts        = new int[4]; // sets up array to hold 4 y points
        bulletsLeft = 0;          // sets bullet count to zero
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
    
    
    /** this method draws the ship */
    public void draw( Graphics g ){
        
        // draw the polgyon that makes the ship
        for(int i = 0; i < 4; i++ ) { 
            xPts[ i ]=( int )( origXPts[ i ] * Math.cos( angle ) - 
                origYPts[ i ] * Math.sin( angle ) + x + .5);    
            
            yPts[ i ] = ( int )( origXPts[ i ] * Math.sin( angle ) + 
                origYPts[ i ] * Math.cos( angle ) + y + .5);    
    }
        // If the game is active draw the ship green  
        if ( active ) {
            
            g.setColor( Color.green );
        }
        
        else {
            g.setColor( Color.darkGray );
            g.fillPolygon( xPts, yPts, 4 );
        }
    }
    
    /** the move method defines all of the movements for the ship */
    public void move(int screenWidth, int screenHeight){
        // determines if there are bullets left to be fired and incriments accordingly
        if( bulletsLeft > 0 ) {
            bulletsLeft--;
        }
        // If turnLeft is true the angle of the ship is adjusted to turn the ship left
        if( turnLeft ) {
            angle -= rotation;
        }
        // If turnRight is true the angle of the ship is adjusted to turn the ship right
        if( turnRight ) {
            angle += rotation;
        }
        // maintains the acuacy of the angle of the ship
        if( angle > ( 2 * Math.PI )) {
            angle -= ( 2 * Math.PI );
        }
        // maintains the acuacy of the angle of the ship
        else if( angle < 0 ) {
            angle += ( 2 * Math.PI );
        }
        // If the ship is accelerating this adds the velocity in the angle the ship is pointed
        if( accelerate ){ 
            xVelocity += acceleration * Math.cos ( angle );
            yVelocity += acceleration * Math.sin ( angle );
        }
        // accelerates the ship
        x += xVelocity;
        y += yVelocity;
        // slows the ship down using decay after the user has stopped accelerating
        xVelocity *= velocityDecay;  
        yVelocity *= velocityDecay;
        
        // Wraps the ship to the opposite side of the screen
        if( x < 0 ) {
            x += screenWidth;
        }
        
        else if( x > screenWidth ) {
            x -= screenWidth;
        }
        
        if( y < 0 ) {
            y += screenHeight;
        }
        
        else if( y > screenHeight ) {
            y -= screenHeight;
        }
        
    }

    public Bullets fire() {
        bulletsLeft = bulletDelay;
        
        return new Bullets ( 10, x, y, angle, xVelocity, yVelocity );
    }
    
    
    public boolean isActive() {
        return active;
    }
    
    // check to see if the ship has any bullets left to shoot
    public boolean canShoot() {
        
        if ( bulletsLeft > 0 ) {
            return false;
        } 
        else {
            return true;
        }
    }
    
}

