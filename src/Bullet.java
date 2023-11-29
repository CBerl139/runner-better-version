import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class Bullet extends AnimatedThing{
    private BulletAnimation animation;
    private boolean hasHitFoe;
    private double timeUntilCanBeShot;
    public Bullet(Pane pane, Hero shootingHero, double x, double y, int maximumIndex, int sizeOfWindowWidth, int sizeOfWindowHeight, int offsetBetweenEachFrame, String fileName) {
        super(pane,x, y, maximumIndex, sizeOfWindowWidth, sizeOfWindowHeight, offsetBetweenEachFrame,new Rectangle2D(x,y,40,25), fileName);
        this.animation = new BulletAnimation(this);
        pane.getChildren().add(animation);

        //this.x = shootingHero.x + 20;
        this.y = - 100;   //offset by 30 so it doesn't shoot out of the hero's head and thus can hit the foes
        this.hasHitFoe = true;
        this.timeUntilCanBeShot = FIRE_RATE;    //Can't shoot at the start
    }

    @Override
    public void update(long time){
        //place holder
    }
    public void update(FoeList foeList, Hero hero, long time) {
        this.x += HERO_SPEED_X + BULLET_SPEED * (hasHitFoe?0:1);    //stops the bullet if it has hit a foe
        for (Foe foe : foeList){
            if (isInCollisionWithFoe(foe)){
                foe.setHitByThing(true);
                hasHitFoe = true;
            }
        }
        if (hasHitFoe) {
            this.x = hero.getX() - 50;
            this.y = -100;// BACKGROUND_HEIGHT + 50;
        }
        if (timeUntilCanBeShot > 0){
            timeUntilCanBeShot--;
        }
        hitBox = new Rectangle2D(x,y,this.hitBox.getWidth(),this.hitBox.getHeight());
        animation.update(time);
    }
    public void render(double cameraX, double cameraY){
        animation.render(cameraX,cameraY);
    }
    public void shoot(Hero shootingHero){
        timeUntilCanBeShot = FIRE_RATE;
        this.hasHitFoe = false;
        this.x = shootingHero.getX() + 50;
        this.y = shootingHero.getY() + 30;
    }
    private boolean isInCollisionWithFoe(Foe foe){
        return this.hitBox.intersects(foe.getHitBox());
    }
    public BulletAnimation getAnimation(){
        return animation;
    }
    public Boolean getHasHitFoe(){
        return hasHitFoe;
    }
    public double getTimeUntilCanBeShot(){
        return timeUntilCanBeShot;
    }
}
