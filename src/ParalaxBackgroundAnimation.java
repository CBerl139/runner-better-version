import javafx.scene.layout.Pane;

public class ParalaxBackgroundAnimation implements GlobalConstants{
    private StaticThing sky;
    private StaticThing leftMountains;
    private StaticThing leftClouds;
    private StaticThing leftForeground;
    private StaticThing rightMountains;
    private StaticThing rightClouds;
    private StaticThing rightForeground;
    private StaticThing leftRocks;
    private StaticThing rightRocks;
    public ParalaxBackgroundAnimation(Pane pane){
        sky = new StaticThing(pane,0,0,100,"file:./img/sky.png");
        leftClouds = new StaticThing(pane,15,20,0,"file:./img/clouds.png");
        rightClouds = new StaticThing(pane,15,20,800,"file:./img/clouds.png");
        leftMountains = new StaticThing(pane,50,75,0,"file:./img/mountains.png");
        rightMountains = new StaticThing(pane,50,75,800,"file:./img/mountains.png");
        leftForeground = new StaticThing(pane,100,100,0,"file:./img/foreground.png");
        rightForeground = new StaticThing(pane,100,100,800,"file:./img/foreground.png");
        leftRocks = new StaticThing(pane,150,100,0,"file:./img/rocks.png");
        rightRocks = new StaticThing(pane,150,100,800,"file:./img/rocks.png");

    }
    public void render(double cameraX, double cameraY){
        leftForeground.render(cameraX,cameraY);
        leftMountains.render(cameraX,cameraY);
        leftClouds.render(cameraX,cameraY);
        rightForeground.render(cameraX,cameraY);
        rightMountains.render(cameraX,cameraY);
        rightClouds.render(cameraX,cameraY);
        leftRocks.render(cameraX,cameraY);
        rightRocks.render(cameraX,cameraY);

    }
}
