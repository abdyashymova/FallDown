
package mazeRunner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ScoreButtonPanel
        extends ButtonPanel {

    ScorePanel panel;

    public ScoreButtonPanel(String name, int score){
        HighScore highScore = new HighScore(score, name);
        panel = new ScorePanel(score, highScore.getScores(), highScore.getNames());

        playAgain.setBounds(550, 550, 100, 50);
        quit.setBounds(550, 605, 100, 50);
        panel.setBounds(0, 0, 1280, 720);
        highscores.setBounds(0, 0, 1280, 720);

        try {
            SwingUtilities.invokeAndWait(new Runnable(){
                @Override
                public void run() {
                    add(playAgain);
                    add(quit);
                    add(panel);
                    add(highscores);
                    setVisible(true);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {}

        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOpen(false);
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
