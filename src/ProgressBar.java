import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProgressBar extends ImageView implements GlobalConstants{
    private Rectangle2D spriteArea;
    private double x;
    private double y;
    public ProgressBar(Pane pane, double x, double y, String fileName){
        super(new Image(fileName));
        pane.getChildren().add(this);
        this.x = x;
        this.y = y;
    }
    public void update(Hero hero, long time){
        this.spriteArea = new Rectangle2D(0,12 * Math.floor(20 * hero.getX()/FINISH_LINE_X),388,12);
    }
    public void render(){
        this.setViewport(spriteArea);
        this.setX(this.x);
        this.setY(this.y);
    }
}
