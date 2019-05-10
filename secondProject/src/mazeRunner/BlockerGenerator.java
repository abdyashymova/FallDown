
package mazeRunner;


import java.util.ArrayList;

public class BlockerGenerator {
    private ArrayList<Blocker> blockers;
    private int numBlockers;				//Blockers that will be creates in total.
    private int currentBlockers = 0;		//Blockers already created.
    private int attempts = 0;               //generator eventually gives up if creating all the blockers seems impossible

    public BlockerGenerator(ArrayList<Blocker> blockers, int numBlockers){
        this.blockers    = blockers;
        this.numBlockers = numBlockers;
        generateBlockers();
    }
 private void generateBlockers(){
        while (currentBlockers < numBlockers && attempts < 100000){
            Integer[] prospectiveLocation = new Integer[2];
            prospectiveLocation[0] = new Integer((int) (Math.random() * 930 + 100));
            prospectiveLocation[1] = new Integer((int) (Math.random() * 720 - 100));
            checkLocationLegal(prospectiveLocation);
        }

    }

    /**
     * Ensures that the new location of a blocker is not near any other blockers.
     * Creates new blocker if the location is usable.
     * @param intended location of the new Blocker
     */
    private void checkLocationLegal(Integer[] location){
        int[] center = new int[] {location[0] + Blocker.IMAGE_SIZE / 2, location[1] + Blocker.IMAGE_SIZE / 2};
        double radius = Math.random() * 35 + 40;
        for(int i = 0; i < blockers.size(); i++){
            int[] otherCenter = new int[] {blockers.get(i).getXPos() + Blocker.IMAGE_SIZE / 2, blockers.get(i).getYPos() + Blocker.IMAGE_SIZE / 2};
            if(Math.sqrt(Math.pow(center[0] - otherCenter[0], 2) + Math.pow(center[1] - otherCenter[1], 2)) < radius + blockers.get(i).r + 20){
                attempts++;
                return;
            }
        }
        blockers.add(new Blocker(location[0], location[1], radius));
        currentBlockers++;
        attempts = 0;
    }

}
