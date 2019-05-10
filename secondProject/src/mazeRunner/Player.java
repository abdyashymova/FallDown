package mazeRunner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.GeneralPath;
import java.awt.geom.IllegalPathStateException;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * Player character in Maze Runner game.
 * extends Flying in order to move in according
 * to Newtonian Mechanics.  Image for player
 * is drawn using geometry package and the drawing
 * is rotated based on the angle.

 */
public class Player
        extends Flying {

    BufferedImage image;
    GeneralPath path;						//used to draw the image of the player

    /*x and y positions that make up the player's basic shape */
    static final int[] X_POINTS = new int[] {40, 70, 40};
    static final int[] Y_POINTS = new int[] {40, 50, 60};

    /*The location of the points that define the players shape after being rotated to the player's angle. */
    double[] displayX = new double[3];
    double[] displayY = new double[3];


    public Player(int xPos, int yPos) {
        super(xPos, yPos);
        dragAmount = .95;
        hitBox = new Line2D[3];
    }

    /**
     * Draws the image for the player.
     * Also updates the position of the player
     * for a later call of updateHitBox().
     * @param angle that the player is pointing
     */
    public void drawPlayer(double angle){
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO, 3);
        for(int i = 0; i < X_POINTS.length; i++){
            double r = Math.sqrt(Math.pow(X_POINTS[i] - 50, 2) + Math.pow(Y_POINTS[i] - 50, 2));
            double oldAngle = Math.atan2(-(Y_POINTS[i] - 50), X_POINTS[i] - 50);
            displayX[i] = (50 + r * Math.cos(angle - oldAngle));
            displayY[i] = (50 - r * Math.sin(angle - oldAngle));
            try{
                path.lineTo(displayX[i], displayY[i]);
            }catch (IllegalPathStateException e) {path.moveTo(displayX[0], displayY[0]);}
        }
        path.closePath();
        Graphics2D g = ((Graphics2D) image.getGraphics());
        g.setRenderingHints(ImageResources.AA);
        g.setColor(Color.RED);
        g.fill(path);
        g.dispose();

    }

    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Updates the hit box to the player's current location and rotation.
     */
    @Override
    public void updateHitBox() {
        for(int i = 0; i < hitBox.length - 1; i++)
            hitBox[i] = new Line2D.Double(getXPos() + displayX[i], getYPos() + displayY[i], getXPos() + displayX[i + 1], getYPos() + displayY[i + 1]);
        hitBox[hitBox.length - 1] = new Line2D.Double(getXPos() + displayX[hitBox.length - 1], getYPos() + displayY[hitBox.length - 1], getXPos() + displayX[0], getYPos() + displayY[0]);
    }
    /**
     * Moves player to a random place on the screen. This method is no longer used.
     */
    public void hyperSpace() {
        setTruePosition((int) (Math.random() * 1280), (int) (Math.random() * 720));
    }

}

