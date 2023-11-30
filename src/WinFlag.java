import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WinFlag implements GlobalConstants{
    private double x;
    private double y;
    private ImageView animation;
    private Rectangle2D hitBox;
    private GameScene gameScene;
    public WinFlag(Pane pane,GameScene gameScene, double x, double y, String fileName){
        this.x = x;
        this.y = y;
        this.gameScene = gameScene;
        this.animation = new ImageView(new Image(fileName));
        pane.getChildren().add(this.animation);
        //set hitbox and make sure it spans across the whole height of the screen :
        this.hitBox = new Rectangle2D(x,y - 200,50,400);
    }
    public void update(Hero hero, long time){
        //finish game when hero collects flag
        if (this.hitBox.contains(hero.getX(),hero.getY())){
            getCollected(hero);
        }
    }
    public void render(double cameraX, double cameraY){
        animation.setX(0 + (this.getX() - cameraX));

        boolean isCameraTooHigh = cameraY < - (BACKGROUND_HEIGHT - WINDOW_HEIGHT);
        boolean isCameraTooLow = cameraY > 0;
        //animation.setY(250 + this.getY());

        if (!(isCameraTooHigh || isCameraTooLow)) {
            animation.setY(250 + YOFFSET + (this.getY() - cameraY));
        }
    }
    public void getCollected(Hero hero){
        //finish game when hero collects flag :
        gameScene.finishGame(hero.getNumberOfHearts());
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle2D getHitBox(){
        return hitBox;
    }

}
