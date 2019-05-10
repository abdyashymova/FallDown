
package mazeRunner;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ButtonPanel
        extends JPanel {
    JButton okayButton = new JButton("OK");
    JButton playAgain = new JButton("Play Again");
    JButton quit = new JButton("Quit");

    JLabel instructions = new JLabel(ImageResources.instructions);
    JLabel highscores   = new JLabel(ImageResources.highscores);


    Object lock = new Object();						//used for wait.

    private boolean open;

    public ButtonPanel(){
        setOpen(true);
        setLayout(null);
        setSize(1280, 720);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
