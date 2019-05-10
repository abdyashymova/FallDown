package mazeRunner;

import java.awt.Image;
import java.awt.geom.Line2D;

/**
 * The purpose of this class is to create an entity with a horizontal hitBox of 1
 * height and argument width or a vertical hitBox of one width and argument height.
 * This can be used to detect when any other entity passes this line.
 * This class is ideal for making the borders of a map as it
 * can detect when an entity is touching the edge.
 */
public class MapBorder extends Entity {

    static final boolean HORIZONTAL = true;
    static final boolean VERTICAL   = false;

    int length;
    boolean direction;

    /**
     * @param x axis position of the start of the hitBox.
     * @param y axis position of the start of the hitBox.
     * @param length of the hitBox
     * @param direction of the hitBox (horizontal or vertical).
     */
    public MapBorder(int xPos, int yPos, int length, boolean direction) {
        super(xPos, yPos);
        this.length = length;
        this.direction = direction;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void updateHitBox() {
        if(direction)
            hitBox = new Line2D[]{new Line2D.Double(getXPos(), getYPos(), getXPos() + length, getYPos())};
        else
            hitBox = new Line2D[]{new Line2D.Double(getXPos(), getYPos(), getXPos(), getYPos() + length)};
    }

}

