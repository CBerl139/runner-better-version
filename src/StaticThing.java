import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StaticThing extends ImageView implements GlobalConstants{
    //speeds are percentages of camera speed
    private double cameraSpeedPercentageX;
    private double cameraSpeedPercentageY;
    //start offset for right backgrounds
    private double startOffset;

    public StaticThing(Pane pane, double cameraSpeedPercentageX,double cameraSpeedPercentageY, double startOffset, String fileName){
        super(new Image(fileName));
        pane.getChildren().add(this);
        this.cameraSpeedPercentageX = cameraSpeedPercentageX;
        this.cameraSpeedPercentageY = cameraSpeedPercentageY;
        this.startOffset = startOffset;
    }
    public void render(double cameraX, double cameraY){
        //move to the left at speed cameraSpeedPercentageX
        this.setX(startOffset - (cameraX * cameraSpeedPercentageX/100)%800);
        this.setY(YOFFSET - cameraY * cameraSpeedPercentageY/100);
    }
}
