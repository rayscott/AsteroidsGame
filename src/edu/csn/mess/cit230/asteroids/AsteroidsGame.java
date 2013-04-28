package edu.csn.mess.cit230.asteroids;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Ray Scott
 * @version 3-19-2013
 */
public class AsteroidsGame extends Applet implements KeyListener, Runnable {

    private static final int WINDOW_WIDTH       = 500;
    private static final int WINDOW_HEIGHT      = 500;
    private static final long FRAMES_PER_SECOND = 50;
    private Thread     gameLoop;           // game loop thread
    private Image      offScrnImg;         // for the off screen images
    private Dimension  dimension;
    private Graphics   g;
    private Ship       ship;       // create a ship
    private Asteroid[] asteroids;  // create the array for asteroids
    private Bullets[]  bullet;     // create array for bullets
    private int        numAstrd;   // number of asteroids
    private int        numBullets;  // number of bullets
    private double     astrdRadius; // asteroid radius
    private double     minAstrdVel; // minimum asteroid velocity
    private double     maxAstrdVel; // maximum asteroid velocity
    private long       endTime;     // 
    private long       startTime;   //
    private long       frame;       //
    private boolean    shooting;    // to tell if ship is shooting
    private boolean    isRunning;   // to tell if game is running

    @Override
    public void init() {
        
        dimension = new Dimension(500, 500);
        resize(dimension.width, dimension.height);
        

        numAstrd    = 10;   
        astrdRadius = 50;   
        minAstrdVel = .10;  
        maxAstrdVel = 5;    
        astrdRadius = 50;   
        startTime   = 0;    
        endTime     = 0;    
        frame       = 50;
        
        // create a new bullet array with ten bullets
        bullet = new Bullets[10];
        
                // create the payers ship
        ship = new Ship(250, 250, 0, .25, .98, .15, 12);
        numBullets = 0;        // start with no bullets on the screen
        shooting = false;      // when game starts ship is not shooting

        asteroids = new Asteroid[100];
        
        // populate the screen with asteroids
        for (int i = 0; i < numAstrd; i++) {
            asteroids[ i] = new Asteroid(Math.random() * dimension.width,
                    Math.random() * dimension.height, astrdRadius, minAstrdVel,
                    maxAstrdVel);
        }

        addKeyListener(this);
        dimension.getSize();
        offScrnImg = createImage(dimension.width, dimension.height);
        g = offScrnImg.getGraphics();
        //gameLoop = new Thread(this);
        //gameLoop.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main( String[] args ) {
        
        final Frame window = new Frame( "Asteroids" );
        final Applet game = new AsteroidsGame();
        
        window.setSize( WINDOW_WIDTH, WINDOW_HEIGHT );
        window.setResizable( false );
        window.setLocationRelativeTo(null);
        window.addWindowListener( new WindowAdapter() {
        
            @Override
            public void windowClosing( WindowEvent e) {
                game.stop();
                window.dispose();
            }
        } );
        
        window.add( game );
        
        game.init();
        window.setVisible(true);
        game.start();
    }

    @Override
    public void start() {



    }

    @Override
    public void stop() {
        
        isRunning = false;
        gameLoop  = null;
    }

    @Override
    public void run() {

        startTime = System.currentTimeMillis();

        ship.move(dimension.width, dimension.height);

        for (int i = 0; i < numBullets; i++) {
            bullet[ i].move(dimension.width, dimension.height);

            if (bullet[ i].getBulletDie() <= 0) {
                deleteBullet(i);
                i--;
            }
        }

        updateAsteroids();

        if (shooting && ship.canShoot()) {
            bullet[ numBullets] = ship.fire();
            numBullets++;
        }

        repaint();

        try {
            endTime = System.currentTimeMillis();
            if (frame - (endTime - startTime) < 0) {
                Thread.sleep(1000L / FRAMES_PER_SECOND);
            }
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void update(Graphics grfx) {

        paint(grfx);

    }

    @Override
    public void paint(Graphics grfx) {

        grfx.setColor(Color.BLACK);
        grfx.fillRect(0, 0, 500, 500);

        for (int i = 0; i < numAstrd; i++) {
            asteroids[ i].draw(grfx);
        }

        ship.draw(grfx);

        grfx.drawImage(offScrnImg, 0, 0, this);

    }

    private void updateAsteroids() {

        for (int i = 0; i < numAstrd; i++) {
            asteroids[ i].move(dimension.width, dimension.height);

            if (asteroids[ i].collision(ship)) {
                numAstrd = 0;
                stop();

                return;
            }

            for (int b = 0; b < numBullets; b++) {
                if (asteroids[ i].bulletHit(bullet[ b])) {

                    deleteBullet(b);
                    deleteAsteroid(i);
                    b = numBullets;
                }
            }
        }
    }

    private void deleteBullet(int index) {

        numBullets--;
        for (int i = index; i < numBullets; i++) {
            bullet[ i] = bullet[ i + 1];
        }
        bullet[ numBullets] = null;
    }

    private void deleteAsteroid(int index) {

        numAstrd--;
        for (int i = index; i < numAstrd; i++) {
            asteroids[ i] = asteroids[ i + 1];
        }
        asteroids[ numAstrd] = null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                ship.setAccelerate(true);

                break;

            case KeyEvent.VK_LEFT:
                ship.setTurnLeft(true);

                break;

            case KeyEvent.VK_RIGHT:
                ship.setTurnRight(true);

                break;

            case KeyEvent.VK_SPACE:
                shooting = true;

                break;

            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                ship.setAccelerate(false);

                break;

            case KeyEvent.VK_LEFT:
                ship.setTurnLeft(false);

                break;

            case KeyEvent.VK_RIGHT:
                ship.setTurnRight(false);

                break;

            case KeyEvent.VK_SPACE:
                shooting = false;

                break;

            default:
                break;

        }
    }
}
