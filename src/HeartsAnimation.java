import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HeartsAnimation extends ImageView{
    private double x;
    private double y;
    private Hearts hearts;
    private Rectangle2D spriteArea;
    public HeartsAnimation(Hearts hearts,double x, double y){
        super(new Image("file:./img/heart-spritesheet.png"));
        this.hearts = hearts;
        this.x = x;
        this.y = y;
    }
    public void update(Hero hero, long time){  //calculate viewport position
        this.spriteArea = new Rectangle2D(0,12 * hero.getNumberOfHearts(),36,12);
    }
    public void render(){
        this.setViewport(this.spriteArea);
        this.setX(this.x);
        this.setY(this.y);
    }

    public void update(int numberOfLives){
        this.spriteArea = new Rectangle2D(0,12 * numberOfLives,36,12);

    }
    public void render(int numberOfLives, double x , double y){
        this.setViewport(this.spriteArea);

    }
}
