package mazeRunner;

public abstract class Flying
        extends AngularMomentum{

    private double xVelocity;
    private double yVelocity;

    double dragAmount;						//The amount velocity is multiplied by because of drag.

    public Flying(int xPos, int yPos) {
        super(xPos, yPos);
        resetVelocity();
    }

    /**
     * Object changes position based on velocity.
     * @param lastTime The time in milliseconds of the last frame/move.
     * @param curTime The time in milliseconds of the current frame/move.
     */
    @Override
    public void move(long lastTime, long curTime){
        double scaleTime = (curTime - lastTime) * .06;
        increaseTruePosition(xVelocity * scaleTime, yVelocity * scaleTime);
        setIntegerPosition();
    }

    /**
     * Velocity is set to zero, object stops.
     */
    public void resetVelocity(){
        xVelocity = 0;
        yVelocity = 0;
    }

    /**
     * @return Horizontal velocity
     */
    public double getXVelocity(){
        return xVelocity;
    }

    /**
     * @return Vertical velocity
     */
    public double getYVelocity(){
        return yVelocity;
    }

    /**
     * @param vertical velocity of the Flying object.
     */
    public void setYVelocity(double velocity){
        yVelocity = velocity;
    }

    /**
     * Increases velocity of Object
     * @param angle of velocity increase.
     * @param force of velocity increase.
     */
    public void accelerate(double angle, double force){
        xVelocity += Math.cos(angle) * force;
        yVelocity += Math.sin(angle) * force;
    }

    /**
     * Decreases yVelocity to simulate effects of gravity.
     */
    public void gravity(){
        yVelocity -= .15;
    }

    /**
     * Slows down objects x and y velocity based on drag.
     * DragAmount must be given a value, this should usually
     * be set in the sub-class.
     */
    public void drag(){
        xVelocity *= dragAmount;
        yVelocity *= dragAmount;
    }
}

