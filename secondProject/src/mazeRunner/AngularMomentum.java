package mazeRunner;

import java.awt.*;

public class AngularMomentum  extends Character{

    public AngularMomentum(int xPos, int yPos) {
        super(xPos, yPos);
        setTruePosition(xPos, yPos);
    }

    @Override
    public void updateHitBox() {

    }

    @Override
    public void move(long lastTime, long curTime) {

    }

    private double trueX;
    private double trueY;

    /**
     * sets position as a double
     * @param trueX xPosition of Character
     * @param trueY yPosition of Character
     */
    public void setTruePosition(double trueX, double trueY){
        this.trueX = trueX;
        this.trueY = trueY;
    }

    /**
     * Change current true position by set amount.
     * @param xIncrease amount Character moves right (negative moves left)
     * @param yIncrease amount Character moves up (negative moves down)
     */
    public void increaseTruePosition(double xIncrease, double yIncrease){
        trueX += xIncrease;
        trueY -= yIncrease;
    }

    /**
     * Sets displayable position based on true position.
     */
    public void setIntegerPosition(){
        setXPos((int) trueX);
        setYPos((int) trueY);
    }

    @Override
    public Image getImage() {
        return null;
    }
}

