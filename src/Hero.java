import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Random;

public class Hero extends AnimatedThing implements GlobalConstants{
    private double speedY;
    private final double accelerationY = 9.8/100;
    private int numberOfJumpsLeft;
    private double invincibility;
    private HeroAnimation animation;
    private String fileNameTransparent;
    private int numberOfHearts;
    private double shootingAnimationDuration;
    private Boolean godMode;
    public Hero(Pane pane, double x, double y, int maximumIndex, int sizeOfWindowWidth, int sizeOfWindowHeight, int offsetBetweenEachFrame, String fileName, String fileNameTransparent){
        super(pane,x,y,maximumIndex,sizeOfWindowWidth,sizeOfWindowHeight,offsetBetweenEachFrame,fileName);
        this.fileNameTransparent = fileNameTransparent;
        this.animation = new HeroAnimation(this,maximumIndex);
        pane.getChildren().add(animation);

        this.invincibility = 0.0;
        this.numberOfJumpsLeft = MAX_NUMBER_OF_JUMPS;
        this.setBehavior(Behavior.RUNNING);
        this.numberOfHearts = 3;

        this.godMode = false;
    }
    public void update(long time){
        //move hero to the right
        this.x += HERO_SPEED_X; //How many pixels does the hero moves per frame
        //jump is handled in inputmanager class

        boolean isOnTheGround = this.y > 0;
        boolean isOffTheGround = this.y < 0;

        //Gravity
        if (isOnTheGround) {
            //make sure it is exactly on the ground
            this.y = 0;
            //Reset the speed when the hero touches the ground
            this.speedY = 0;
            //reset the number of jumps the hero has left
            this.numberOfJumpsLeft = MAX_NUMBER_OF_JUMPS;
            //change hero behavior from jumping to running
            switch(this.getBehavior()) {
                case JUMPING -> this.setBehavior(Behavior.RUNNING);
                case SHOOTING_JUMPING -> this.setBehavior(Behavior.SHOOTING);
            }
        }else if (isOffTheGround) {
            this.speedY += this.accelerationY;
        }
        this.y += this.speedY;

        if(invincibility > 0) {
            this.invincibility--;
        }

        //Shooting animation cancel
        if (shootingAnimationDuration > 0){
            shootingAnimationDuration--;
        } else if (shootingAnimationDuration == 0) {
            switch (this.getBehavior()){
                case SHOOTING -> this.setBehavior(Behavior.RUNNING);
                case SHOOTING_JUMPING -> this.setBehavior(Behavior.JUMPING);
            }
        }

        hitBox = new Rectangle2D(x + 15,y,this.hitBox.getWidth(),this.hitBox.getHeight());
        animation.update(time);

    }
    public void render(double cameraX, double cameraY){
        animation.render(cameraX,cameraY);
    }
    public void jump(){
        if (this.numberOfJumpsLeft > 0){
            this.speedY -= JUMP_HEIGHT;
            //change behavior of hero from running to jumping
            switch (this.getBehavior()) {
                case RUNNING -> this.setBehavior(Behavior.JUMPING);
                case SHOOTING -> this.setBehavior(Behavior.SHOOTING_JUMPING);
            }
            numberOfJumpsLeft--;
        }
    }
    public void toggleBehavior(){
        switch (this.getBehavior()){
            case RUNNING -> this.setBehavior(Behavior.SHOOTING);
            case JUMPING -> this.setBehavior(Behavior.SHOOTING_JUMPING);
            case SHOOTING -> this.setBehavior(Behavior.RUNNING);
            case SHOOTING_JUMPING -> this.setBehavior(Behavior.JUMPING);
        }
    }

    public void shoot(BulletList listOfBullets){
        if (shootingAnimationDuration == 0 && listOfBullets.numberOfCanBeShot() != 0){
            switch (this.getBehavior()){
                case RUNNING -> this.setBehavior(Behavior.SHOOTING);
                case JUMPING -> this.setBehavior(Behavior.SHOOTING_JUMPING);
            }
            //toggleBehavior();
            shootingAnimationDuration = 25;
        }
        listOfBullets.shoot(this);
    }

    public HeroAnimation getAnimation(){
        return this.animation;
    }
    public double getInvincibility() {
        return invincibility;
    }
    public double getSpeedY(){
        return speedY;
    }
    public int getNumberOfHearts(){
        return this.numberOfHearts;
    }
    public Boolean getGodMode(){
        return godMode;
    }
    public String getFileNameTransparent(){
        return fileNameTransparent;
    }
    public void setInvincibility(double invincibility){
        this.invincibility = invincibility;
    }
    public void toggleGodMode(){
        godMode = !godMode;
    }
    public void removeHeart(){
        this.numberOfHearts--;
    }
    public void removeJump(){
        this.numberOfJumpsLeft--;
    }

}
