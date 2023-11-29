import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BulletAnimation extends ImageView implements GlobalConstants{
    private Bullet bullet;
    private Rectangle2D spriteArea;
    public BulletAnimation(Bullet bullet){
        super(new Image(bullet.getFileName()));
        this.bullet = bullet;
        this.setVisible(false);
    }
    public void update(long time){
        this.spriteArea = new Rectangle2D(521,368,40,25);
    }
    public void render(double cameraX, double cameraY){
        this.setVisible(!bullet.getHasHitFoe());
        this.setViewport(spriteArea);

        this.setX(bullet.getX() - cameraX);
        this.setY(250 + YOFFSET + (bullet.getY() - cameraY));
    }
    public void shoot(){
        this.setVisible(true);
    }
}
