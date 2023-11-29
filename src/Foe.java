import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class Foe extends AnimatedThing{
    private boolean isHitByThing;   //thing can be hero or bullet
    private FoeAnimation animation;
    public Foe(Pane pane, double x, double y, int maximumIndex, int sizeOfWindowWidth, int sizeOfWindowHeight, int offsetBetweenEachFrame, String fileName) {
        super(pane,x, y, maximumIndex, sizeOfWindowWidth, sizeOfWindowHeight, offsetBetweenEachFrame,new Rectangle2D(x,y + 25,50,75), fileName);
        this.animation = new FoeAnimation(this,maximumIndex);
        pane.getChildren().add(animation);
    }
    public void update(long time){
        //place holder
    }
    public void update(Hero hero, long time){
        //TODO : decide on which direction they move (or if they move at all)
        this.x += 1;
        if (this.isInCollisionWithHero(hero) && hero.getInvincibility() == 0 && hero.getNumberOfHearts() > 0){
            if (!hero.getGodMode()){
                hero.setInvincibility(INVINCIBILITY_DURATION);
                hero.removeHeart();
            }
            this.setHitByThing(true);
            //Main.sound.playSound("file:./sound/Honking.wav");
        }
        if (hero.getX() - this.x > BACKGROUND_WIDTH / 4 || isHitByThing){   //If foe is BACKGROUND_WIDTH / 4 pixel behind enemy or isHitByThing
            //TODO : figure out what is the best respawn distance
            this.x = hero.getX() + BACKGROUND_WIDTH + BACKGROUND_WIDTH * Math.random();  //Move enemy in front
            this.setHitByThing(false);
            //change the sprite scroll cycle to make it seem like it's a new one (should be in FoeAnimation class, but it's easier to just put it here) :
            animation.setRandomIndexOffset(Math.random());
        }
        hitBox = new Rectangle2D(x + 20,y + 25,this.hitBox.getWidth(),this.hitBox.getWidth());
        animation.update(time);
    }
    public void render(double cameraX, double cameraY){
        animation.render(cameraX,cameraY);
    }
    public boolean isInCollisionWithHero(Hero hero){
        return this.hitBox.intersects(hero.hitBox);
    }
    public FoeAnimation getAnimation(){
        return animation;
    }
    public void setHitByThing(Boolean isHitByThing){
        this.isHitByThing = isHitByThing;
    }
}
