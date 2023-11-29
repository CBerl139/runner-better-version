import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class BulletLoadingBar extends ImageView implements GlobalConstants{
    private BulletList listOfBullet;
    private Rectangle2D spriteArea;
    private double timeUntilCanBeShot;
    private double x;
    private double y;
    public BulletLoadingBar(Pane pane, double x, double y,BulletList listOfBullet,String fileName){
        super(new Image(fileName));
        pane.getChildren().add(this);
        this.listOfBullet = listOfBullet;
        this.x = x;
        this.y = y;
    }
    public void update(long time){
        this.timeUntilCanBeShot = listOfBullet.minTimeUntilCanBeShotNonZero();
        this.spriteArea = new Rectangle2D(0,9 * Math.floor(19 * (1 - timeUntilCanBeShot/FIRE_RATE)),40,9);
    }
    public void render(){
        this.setViewport(spriteArea);
        this.setX(this.x);
        this.setY(this.y);
    }
}
