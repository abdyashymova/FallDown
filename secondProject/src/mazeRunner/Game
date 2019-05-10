package mazeRunner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Game
        extends JPanel
        implements KeyListener{

    static final double PLAYER_ACCELERATION = .5;

    Runnable framePainter;
    long initialTime;
    long lastTime;
    long curTime;

    int hundrethSecond = 0;							   //Hundreths of a second played.
    int tenthSeconds   = 0;							   //tenths of a second played.
    int seconds        = 0;							   //seconds played
    int tensSeconds    = 0;							   //tens seconds played.
    int minutes        = 0;							   //minutes played

    boolean alive;
    boolean notWon;

    /*variables for the player and the player's movement */
    private Player player;
    private double angle = 0;
    private boolean turnRight = false;
    private boolean turnLeft  = false;
    private boolean speedUp = false;

    MapBorder top    = new MapBorder(0,0, 1280, MapBorder.HORIZONTAL);
    MapBorder bottom = new MapBorder(0, 720, 1280, MapBorder.HORIZONTAL);
    MapBorder left   = new MapBorder(0, 0, 720, MapBorder.VERTICAL);
    MapBorder right   = new MapBorder(1200, 0, 720, MapBorder.VERTICAL);

    long lastTimeHitBorder = -100;    //Last time where the player hit the border of the map.

    private ArrayList<Blocker> blockers = new ArrayList<Blocker>();

    /**
     * Create a new game that can be played.
     * @param framePainter a runnable object that can be run to paint the game.
     */
    public Game(Runnable framePainter){
        this.framePainter = framePainter;
        alive  = true;
        notWon = true;
        player = new Player(0, 300);
        initialTime = System.currentTimeMillis();
        lastTime = initialTime;
        top.updateHitBox();
        bottom.updateHitBox();
        left.updateHitBox();
        right.updateHitBox();
    }

    /**
     * Runs the main game loop to play the game.
     * @return The players final score at the end of the game.
     */
    public int play(){
        /*This can be used to generate an exact number of blockers,
         * but, instead, it will generate as many blockers as will fit
         * on the screen.
         */
        new BlockerGenerator(blockers, 50);


        for(int i = 0; i < blockers.size(); i++){
            blockers.get(i).updateHitBox();
        }

        while(alive  && notWon){
            curTime = System.currentTimeMillis();
            /* times the game/frame updates to at most 60Hz. */
            while(curTime - lastTime < 16){
                try {
                    Thread.sleep(16 - curTime + lastTime);
                } catch (InterruptedException e) {}
                curTime = System.currentTimeMillis();
            }
            SwingUtilities.invokeLater(framePainter);		//Paint the current frame.
            if(turnRight)
                angle -= Math.PI / 60;
            if(turnLeft)
                angle += Math.PI / 60;
            if(speedUp)
                player.accelerate(angle, PLAYER_ACCELERATION);
            movePlayer(lastTime, curTime);
            playerInMap();
            hitBlocker();
            updateTimeCounter();
            lastTime = curTime;
        }
        if(!alive)
            return 0;
        int score = (int) (100 - (curTime - initialTime) / 1000);
        if (score < 1)
            return 1;
        return score;
    }

    /**
     * Updates the variable used to display the counter in the top left of the screen.
     */
    private void updateTimeCounter(){
        String time = "0000" + Long.toString(curTime - initialTime);
        hundrethSecond = time.charAt(time.length() - 2) - 48;
        tenthSeconds = time.charAt(time.length() - 3) - 48;
        seconds = time.charAt(time.length() - 4) - 48;
        tensSeconds = time.charAt(time.length() - 5) - 48;
    }

    /**
     * Performs the player's movement.
     * @param curTime
     * @param lastTime
     */
    private void movePlayer(long lastTime, long curTime){
        player.move(lastTime, curTime);
        player.drag();
        player.drawPlayer(angle);
        player.updateHitBox();
    }

    /**
     * Checks if the player hits a blocker.
     */
    private void hitBlocker(){
        for(int i = 0; i < blockers.size(); i++){
            if(player.entityHit(blockers.get(i))){
                alive = false;
            }
        }
    }

    /**
     * Prevents the player from leaving the map borders
     */
    private void playerInMap(){
        if(player.entityHit(top) && curTime - lastTimeHitBorder > 80){
            player.setYVelocity(player.getYVelocity() * -1 - 1);
            angle += 2 * Math.atan2(player.getYVelocity(), player.getXVelocity());
            lastTimeHitBorder = curTime;
        }
        else if(player.entityHit(bottom) && curTime - lastTimeHitBorder > 5){
            player.setYVelocity(player.getYVelocity() * -1 + 1);
            angle += 2 * Math.atan2(player.getYVelocity(), player.getXVelocity());
            lastTimeHitBorder = curTime;
        }
        if(player.entityHit(left)){
            angle = 0;
            player.setTruePosition(0, 300);
            player.resetVelocity();
        }
        else if(player.entityHit(right)){
            notWon = false;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < blockers.size(); i++)
            g.drawImage(blockers.get(i).getImage(), blockers.get(i).getXPos(), blockers.get(i).getYPos(), this);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 100, 720);
        g.setColor(Color.RED);
        g.fillRect(1200, 0, 80, 720);
        g.drawImage(player.getImage(), player.getXPos(), player.getYPos(), this);
        g.setColor(Color.BLACK);
        g.drawString(minutes + ":" + tensSeconds + seconds + ":" + tenthSeconds + hundrethSecond, 20, 20);
    }

    /**
     * Method not used, necessary to implement Key Listener.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            turnRight = true;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            turnLeft = true;
        else if(e.getKeyCode() == KeyEvent.VK_Z)
            speedUp = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            turnRight = false;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            turnLeft = false;
        else if(e.getKeyCode() == KeyEvent.VK_Z)
            speedUp = false;
    }

}

