package mazeRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HighScore {
    private Scanner scan;
    private PrintWriter writer;

    private int newScore;
    private String newName;
    private int[] scores = new int[10];
    private String[] names = new String[10];

    private int indexScoreAdded;             //the index where a new score was added
    private boolean scoreAdded = false;

    public HighScore(int newScore, String newName){
        this.newScore = newScore;
        this.newName = newName;
        initializeOldScores();
        initializeOldNames();
        readScores();
        readNames();
        addNewScore();
        addNewName();
        printScores();
        printNames();
    }

    /**
     * @return a ten index int array containing the current high scores.
     */
    public int[] getScores(){
        return scores;
    }

    /**
     * @return The names of the people with the top ten scores.
     */
    public String[] getNames(){
        return names;
    }

    /**
     * Set all scores to an initial value of zero.
     */
    private void initializeOldScores(){
        for(int i = 0; i < scores.length; i++){
            scores[i] = 0;
        }
    }

    /**
     * Set all names to an initial value of unplayed.
     */
    private void initializeOldNames(){
        for(int i = 0; i < names.length; i++){
            names[i] = "Unplayed";
        }
    }

    /**
     * Reads high scores from the high score file.
     */
    private void readScores(){
        try{
            scan = new Scanner(new File("HighScores.txt"));
            for(int i = 0; i < scores.length; i++){
                if(!scan.hasNext())
                    break;
                scores[i] = scan.nextInt();
            }
            scan.close();
        }catch(IOException e) {initializeOldScores();}
        catch(InputMismatchException e) {scan.close(); initializeOldScores();}
    }

    /**
     * Reads names from file of names
     */
    private void readNames(){
        try{
            scan = new Scanner(new File("Names.txt"));
            for(int i = 0; i < names.length; i++){
                if(!scan.hasNext())
                    break;
                names[i] = scan.nextLine();
            }
            scan.close();
        }catch(IOException e) {initializeOldNames();}
        catch(InputMismatchException e) {scan.close(); initializeOldNames();}
    }

    /**
     * Adds new Score to array of scores.
     * newScore is added at the first index
     * where newScore is larger than the value
     * in scores at that index.  All other values
     * in scores are displaced by one.
     */
    private void addNewScore(){
        for(int i = 0; i < scores.length; i++){
            if(newScore > scores[i]){
                int temp1 = scores[i];
                int temp2;
                scores[i] = newScore;
                indexScoreAdded = i;
                scoreAdded = true;
                for(int j = i + 1; j < scores.length; j++){
                    temp2 = scores[j];
                    scores[j] = temp1;
                    temp1 = temp2;
                }
                break;
            }
        }
    }

    /**
     * Adds new name to array of names.
     * Location corresponds to a score
     * that was added to the score array.
     */
    private void addNewName(){
        if(scoreAdded){
            String temp1 = names[indexScoreAdded];
            String temp2;
            names[indexScoreAdded] = newName;
            for(int i = indexScoreAdded + 1; i < scores.length; i++){
                temp2 = names[i];
                names[i] = temp1;
                temp1 = temp2;
            }
        }
    }


    /**
     * Prints scores to Text File.
     * @throws NullPointerException if any index in scores is null.
     */
    private void printScores(){
        try{
            writer = new PrintWriter("HighScores.txt");
            for(int i = 0; i < scores.length; i++){
                writer.println(scores[i]);
            }
            writer.close();
        } catch (FileNotFoundException e) {}
    }

    /**
     * Prints names to Text File
     * @throws NullPointerException if any index in names is null.
     */
    private void printNames(){
        try{
            writer = new PrintWriter("Names.txt");
            for(int i = 0; i < names.length; i++){
                writer.println(names[i]);
            }
            writer.close();
        } catch (FileNotFoundException e) {}
    }
}

