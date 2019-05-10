package mazeRunner;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Window
        extends JFrame {
    /*Times in milliseconds for current and last frame.*/
    long lastFrame;
    long curFrame;

    private Game game;
    private ButtonPanel panel;

    int score = 0;
    String name;

    public Window(){
        ImageResources.loadImages();
        setupWindow();
        instructions();
        while(true){
            setUpGame();
            score = game.play();
            removeGame();
            scorePanel();
        }
    }

    /**
     * Sets up frame for game to be displayed.
     */
    private void setupWindow(){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run(){
                    setResizable(false);
                    setSize(1280, 720);
                    /*JVM terminates when window is closed */
                    addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                            System.exit(0);
                        }
                    });

                    setVisible(true);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {}
    }

    /**
     * Creates a runnable that causes the frame to repaint.
     * @return A runnable that causes the game to paint the next frame.
     */
    private Runnable timeRepaintRunner(){
        return new Runnable(){
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        };
    }

    /**
     * Sets up game that user will play.
     */
    private void setUpGame(){
        game = new Game(timeRepaintRunner());
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run(){
                    add(game);
                    addKeyListener(game);
                    game.setPreferredSize(new Dimension(1280, 720));
                    requestFocus();
                    pack();
                    setLocationRelativeTo(null);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {}
    }

    /**
     * Opens instruction screen.
     */
    private void instructions(){
        panel = new Instructions();
        addPanel();
        name = ((Instructions) panel).name;
        removePanel();
    }

    private void scorePanel(){
        panel = new ScoreButtonPanel(name, score);
        addPanel();
        removePanel();
    }

    /**
     * adds current ButtonPanel to JFrame.
     */
    private void addPanel(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                add(panel);
                revalidate();
                repaint();
            }
        });

        while(panel.isOpen()){
            synchronized(panel.lock){
                try {
                    panel.lock.wait();
                } catch (InterruptedException e) {}
            }
        }
    }

    /**
     * Removes the current ButtonPanel.
     */
    private void removePanel(){
        try {
            SwingUtilities.invokeAndWait(new Runnable(){

                @Override
                public void run() {
                    remove(panel);
                }
            });
        } catch (InvocationTargetException | InterruptedException e){}
    }

    /**
     * removes game from panel.
     */
    private void removeGame(){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    remove(game);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {}
    }
}
