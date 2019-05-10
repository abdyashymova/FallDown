package mazeRunner;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class ImageResources {

    /* Images for different entities.
     * Created here instead of in each entities class
     * because only one of each Image is needed in memory.
     */
    static final ImageIcon instructions = new ImageIcon("instructions.png");
    static final ImageIcon highscores   = new ImageIcon("HighScores.png");

    /**
     * Loads images into memory.
     * All images are BufferedImage objects.
     * Once this method is called, these images
     * may be accessed from a static context:
     *
     */
    public static void loadImages(){
    }

    /**
     * Takes an argument BufferedImage then returns a new BufferedImage
     * that has been rotated the argument amount.
     * @param image to be rotated
     * @param angle of rotation in radians
     * @return image rotated angle radians.
     */
    public static BufferedImage rotateImage(BufferedImage image, double angle){
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = rotatedImage.createGraphics();
        g.rotate(-angle, image.getWidth() /2, image.getHeight() /2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return rotatedImage;
    }

    static final RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
}

