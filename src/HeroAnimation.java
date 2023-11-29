import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class HeroAnimation extends ImageView implements GlobalConstants{
    private Hero hero;
    private Rectangle2D spriteArea;
    private int index;
    private int maximumIndex;

    public HeroAnimation(Hero hero,int maximumIndex){
        super(new Image(hero.getFileName()));
        this.hero = hero;

        this.maximumIndex = maximumIndex;
        this.index = 0;
    }
    public void update(long time){
        this.index = (int) (10 * time/1000000000)%maximumIndex;   //should be in HeroAnimation update but flemme de tout changer

        if (hero.getInvincibility() != 0){
            this.setImage(new Image("file:./img/heros-transparent.png"));
        }else{
            this.setImage(new Image(hero.getFileName()));
        }
        this.spriteArea = new Rectangle2D((hero.sizeOfWindowWidth) * ((hero.behavior == Behavior.RUNNING || hero.behavior == Behavior.SHOOTING)?this.index:((hero.getSpeedY()<0)?0:1)),
                (hero.sizeOfWindowHeight + hero.offsetBetweenEachFrame) * hero.behavior.ordinal(),hero.sizeOfWindowWidth,hero.sizeOfWindowHeight);
    }
    public void render(double cameraX, double cameraY){
        this.setViewport(spriteArea);

        this.setX((hero.getX() - cameraX));
        this.setY(250 + YOFFSET + (hero.getY() - cameraY)); //imageview is offset by y = 250
    }
}
