import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class FoeAnimation extends ImageView implements GlobalConstants{
    private Foe foe;
    private Rectangle2D spriteArea;
    private int index;
    private int maximumIndex;
    private double randomIndexOffset;
    public FoeAnimation(Foe foe, int maximumIndex){
        super(new Image(foe.getFileName()));
        this.foe = foe;
        this.index = 0;
        this.maximumIndex = maximumIndex;
        this.randomIndexOffset = Math.random();
    }
    public void update(long time){
        //update timer to scroll through the sprites in the sprite sheet every 5th of a second
        this.index = ((int) (5 * time/1000000000 + Math.floor(maximumIndex * randomIndexOffset))%maximumIndex);

        this.spriteArea = new Rectangle2D((foe.sizeOfWindowWidth) * this.index,0,foe.sizeOfWindowWidth,foe.sizeOfWindowHeight);
    }
    public void render(double cameraX, double cameraY){
        this.setViewport(spriteArea);
        this.setX((foe.getX() - cameraX));
        this.setY(250 + YOFFSET + (foe.getY() - cameraY));
    }
    public void setRandomIndexOffset(double randomIndexOffset){
        this.randomIndexOffset = randomIndexOffset;
    }
}
