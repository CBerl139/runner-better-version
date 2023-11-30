import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ArrayList;

enum Behavior{
        RUNNING, JUMPING, SHOOTING, SHOOTING_JUMPING;
    }

public abstract class AnimatedThing implements GlobalConstants{
    double x;
    double y;
    Behavior behavior;
    int sizeOfWindowWidth;
    int sizeOfWindowHeight;
    int offsetBetweenEachFrame;
    Rectangle2D hitBox;
    String fileName;
    public AnimatedThing(Pane pane, double x, double y, int maximumIndex, int sizeOfWindowWidth, int sizeOfWindowHeight , int offsetBetweenEachFrame, String fileName){
        this.x = x;
        this.y = y;
        this.sizeOfWindowHeight = sizeOfWindowHeight;
        this.sizeOfWindowWidth = sizeOfWindowWidth;
        this.offsetBetweenEachFrame = offsetBetweenEachFrame;

        this.hitBox = new Rectangle2D(this.x,this.y,55 ,100);
        this.fileName = fileName;
    }
    public AnimatedThing(Pane pane, double x, double y,int maximumIndex,int sizeOfWindowWidth,int sizeOfWindowHeight ,int offsetBetweenEachFrame, Rectangle2D hitBox, String fileName){
        this.x = x;
        this.y = y;
        this.sizeOfWindowHeight = sizeOfWindowHeight;
        this.sizeOfWindowWidth = sizeOfWindowWidth;
        this.offsetBetweenEachFrame = offsetBetweenEachFrame;

        this.hitBox = hitBox;
        this.fileName = fileName;
    }
    public abstract ImageView getAnimation();
    public abstract void update(long time);
    public abstract void render(double cameraX, double cameraY);
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void setBehavior(Behavior behavior){
        this.behavior = behavior;
    }
    public Rectangle2D getHitBox(){
        return hitBox;
    }
    public String getFileName(){
        return fileName;
    }
}
