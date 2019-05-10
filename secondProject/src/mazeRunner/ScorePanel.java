
package mazeRunner;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScorePanel
        extends JPanel {
    static final Font FONT = new Font("SansSerif", Font.PLAIN, 36);

    int score;
    int[] scores;		//must have ten indexes
    String[] names;		//must have ten indexes

    public ScorePanel(int score, int[] scores, String[] names){
        this.score = score;
        this.scores = scores;
        this.names = names;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setFont(FONT);
        for(int i = 0; i < 5; i++){
            g.drawString((i + 1) + ". " + names[i] + ": " + scores[i], 375, 120 + (75 * i));
        }

        for(int i = 0; i < 5; i++){
            g.drawString( (i + 6) + ". " + names[i + 5] + ": " + scores[i + 5], 675, 120 + (75 * i));
        }

        g.drawString("Current Score: " + score, 500, 500);
    }
}
