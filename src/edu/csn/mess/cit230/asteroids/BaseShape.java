package edu.csn.mess.cit230.asteroids;

import java.awt.*;

/**
 * The Class BaseShape.
 * Intended to be sub-classed to create a variety of vector-based shapes.
 * @version 2013-03-17
 */
public abstract class BaseShape
{
    /**
     * Gets the rectangular bounding box that completely contains the shape.
     * Used for collision detection. Must be implemented by subclasses.
     * @return the shape's bounding box
     */
    public abstract Rectangle getBounds();


    /**
     * Gets the a reference to a Shape object.
     * @return the a reference to a Shape object
     */
    public Shape getShape()
    {
        return shape;
    }

    /**
     * Sets the a reference to a Shape object.
     * @param shape the new a reference to a Shape object
     */
    public void setShape( Shape shape )
    {
        this.shape = shape;
    }

    /**
     * Checks if this object is active and visible.
     * @return the boolean flag indicating if this object is active and visible
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Sets the boolean flag indicating if this object is active and visible.
     * @param alive the new flag indicating if this object is active and visible
     */
    public void setAlive( boolean alive )
    {
        this.alive = alive;
    }

    /**
     * Gets the current x coordinate of this object.
     * @return the current x coordinate of this object
     */
    public double getX()
    {
        return x;
    }

    /**
     * Sets the current x coordinate of this object.
     * @param x the new current x coordinate of this object
     */
    public void setX( double x )
    {
        this.x = x;
    }

    /**
     * Gets the current y coordinate of this object.
     * @return the current y coordinate of this object
     */
    public double getY()
    {
        return y;
    }

    /**
     * Sets the current y coordinate of this object.
     * @param y the new current y coordinate of this object
     */
    public void setY( double y )
    {
        this.y = y;
    }

    /**
     * Gets the current velocity in the x direction.
     *
     * @return the current velocity in the x direction
     */
    public double getVelX()
    {
        return velX;
    }

    /**
     * Sets the current velocity in the x direction.
     * @param velX the new current velocity in the x direction
     */
    public void setVelX( double velX )
    {
        this.velX = velX;
    }

    /**
     * Gets the current velocity in the y direction.
     * @return the current velocity in the y direction
     */
    public double getVelY()
    {
        return velY;
    }

    /**
     * Sets the current velocity in the y direction.
     * @param velY the new current velocity in the y direction
     */
    public void setVelY( double velY )
    {
        this.velY = velY;
    }

    /**
     * Gets the current direction of movement.
     * @return the current direction of movement
     */
    public double getMoveAngle()
    {
        return moveAngle;
    }

    /**
     * Sets the current direction of movement.
     * @param moveAngle the new current direction of movement
     */
    public void setMoveAngle( double moveAngle )
    {
        this.moveAngle = moveAngle;
    }

    /**
     * Gets the current direction the object is facing.
     * @return the current direction the object is facing
     */
    public double getFaceAngle()
    {
        return faceAngle;
    }

    /**
     * Sets the current direction the object is facing.
     * @param faceAngle the new current direction the object is facing
     */
    public void setFaceAngle( double faceAngle )
    {
        this.faceAngle = faceAngle;
    }

    /**
     * Increments the faceAngle.
     * @param inc the inc
     */
    public void incFaceAngle( double inc )
    {
        this.faceAngle += inc;
    }

    /**
     * Increments the moveAngle.
     * @param inc the inc
     */
    public void incMoveAngle( double inc )
    {
        this.moveAngle += inc;
    }

    /**
     * Increments the velocity in the x axis.
     * @param inc the inc
     */
    public void incVelX( double inc )
    {
        this.velX += inc;
    }

    /**
     * Increments the velocity in the y axis.
     * @param inc the inc
     */
    public void incVelY( double inc )
    {
        this.velY += inc;
    }

    /**
     * Increments the x coordinate by a specified amount.
     * @param inc the inc
     */
    public void incX( double inc )
    {
        this.x += inc;
    }

    /**
     * Increments the y coordinate by a specified amount.
     * @param inc the inc
     */
    public void incY( double inc )
    {
        this.y += inc;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument;
     *         false otherwise.
     */
    @Override
    public boolean equals( Object obj )
    {
        boolean result = true; // assume they're equal, then prove otherwise

        if ( ( obj == null ) || ( getClass() != obj.getClass() ) ) {
            result = false;
        } else {
            BaseShape that = ( BaseShape )obj;

            if ( this.alive != that.alive ) {
                result = false;
            } else if ( Double.compare( this.faceAngle, that.faceAngle ) != 0 ) {
                result = false;
            } else if ( Double.compare( this.moveAngle, that.moveAngle ) != 0 ) {
                result = false;
            } else if ( Double.compare( this.velX, that.velX ) != 0 ) {
                result = false;
            } else if ( Double.compare( this.velY, that.velY ) != 0 ) {
                result = false;
            } else if ( Double.compare( this.x, that.x ) != 0 ) {
                result = false;
            } else if ( Double.compare( this.y, that.y ) != 0 ) {
                result = false;
            } else if ( this.shape != null  ) {
                if ( !this.shape.equals( that.shape ) ) {
                    result = false;
                }
            } else {
                if ( ( that.shape != null ) ) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by java.util.HashMap.
     * @return a hash code value for this object
     */
    @Override
    public int hashCode()
    {
        long temp;
        int  result = shape != null ? shape.hashCode() : 0;

        result = 31 * result + ( alive ? 1 : 0 );
        temp   = x != 0.0d ? Double.doubleToLongBits( x ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );
        temp   = y != 0.0d ? Double.doubleToLongBits( y ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );
        temp   = velX != 0.0d ? Double.doubleToLongBits( velX ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );
        temp   = velY != 0.0d ? Double.doubleToLongBits( velY ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );
        temp   = moveAngle != 0.0d ? Double.doubleToLongBits( moveAngle ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );
        temp   = faceAngle != 0.0d ? Double.doubleToLongBits( faceAngle ) : 0L;
        result = 31 * result + ( int )( temp ^ ( temp >>> 32 ) );

        return result;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString()
    {
        return new StringBuilder()
               .append( "BaseShape{" )
               .append( "shape=" ).append( shape )
               .append( ", alive=" ).append( alive )
               .append( ", x=" ).append( x )
               .append( ", y=" ).append( y )
               .append( ", velX=" ).append( velX )
               .append( ", velY=" ).append( velY )
               .append( ", moveAngle=" ).append( moveAngle )
               .append( ", faceAngle=" ).append( faceAngle )
               .append( '}' )
               .toString();
    }

    /**
     * Calculate X movement value based on direction angle.
     * @return movement value in the x axis
     */
    public static double calcAngleMoveX( double angle )
    {
        return Math.cos( angle * Math.PI / 180.0 );
    }

    /**
     * Calculate Y movement value based on direction angle.
     * @return movement value in the y axis
     */
    public static double calcAngleMoveY( double angle )
    {
        return Math.sin( angle * Math.PI / 180.0 );
    }

    /*************************************************************************
     *                    C l a s s   A t t r i b u t e s
     *************************************************************************/

    /**
     * The shape field contains a polymorphic reference to any object
     * that is a java.awt.Shape.
     */
    private Shape shape;

    /** The alive field indicates whether this object is visible and active. */
    private boolean alive;

    /** The x field contains the x coordinate of this object. */
    private double x;

    /** The y field contains the y coordinate of this object. */
    private double y;

    /** The velX field contains the velocity of the object in the x axis. */
    private double velX;

    /** The velY field contains the velocity of the object in the y axis. */
    private double velY;

    /**
     * The moveAngle field contains the direction of travel that
     * this object is moving.
     */
    private double moveAngle;

    /** The faceAngle field contains the direction that this object is facing. */
    private double faceAngle;
}
