
public class Camera implements GlobalConstants{
    private double x;
    private double y;
    private double accelerationX;
    private double accelerationY;
    private double speedX;
    private double speedY;
    //variable that will still allow calculation of Y when the camera is out of bounds :
    private double yOutOfBounds;
    public Camera(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(Hero hero, long time) {
        //if statement to stop camera movement if hero is close to the finish line
        if (FINISH_LINE_X - hero.getX() > WINDOW_WIDTH - 300) {
            this.accelerationX = (0.05 * (hero.getX() - HERO_STARTING_POSITION_X - this.x) + 9 * this.speedX) / 10;
            this.speedX = +this.accelerationX;
            this.x += this.speedX;
        }

        this.accelerationY = (0.05 * (hero.getY() - HERO_STARTING_POSITION_Y - this.yOutOfBounds) + 9 * this.speedY) / 10;
        this.speedY = this.accelerationY;   //TODO problème à régler
        this.yOutOfBounds += this.speedY;
        //bounds are defined by the background's dimension
        boolean isCameraTooHigh = this.yOutOfBounds < - (BACKGROUND_HEIGHT - WINDOW_HEIGHT);
        boolean isCameraTooLow = this.yOutOfBounds > 0;
        //only move the camera if it is in bounds :
        if (!(isCameraTooHigh || isCameraTooLow)) {
            this.y = this.yOutOfBounds;
        }
    }
    @Override
    public String toString(){
        return  "x: " + x +
                "y: " + y;
    }
}
