import javafx.scene.layout.Pane;

public class BackgroundAnimation implements GlobalConstants{
    private StaticThing leftBackground;
    private StaticThing rightBackground;
    public BackgroundAnimation(Pane pane, String fileNameBackground){
        leftBackground = new StaticThing(pane,100,100,0, fileNameBackground);
        rightBackground = new StaticThing(pane,100,100,800,fileNameBackground);

    }
    public void render(double cameraX, double cameraY){
        leftBackground.render(cameraX,cameraY);
        rightBackground.render(cameraX,cameraY);
        }
}

