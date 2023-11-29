import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class BulletList extends ArrayList<Bullet> implements GlobalConstants{
    public BulletList(Pane pane, Hero hero,String fileName){
        super();
        for(int i = 0; i < 3; i++){     //Player can shoot 3 bullets
            this.add(new Bullet(pane,hero,hero.getX(),hero.getY(),1,85,104,
                    59,fileName));
        }
    }
    public void update(FoeList listOfFoes, Hero hero, long time){
        for (Bullet bullet : this) bullet.update(listOfFoes,hero,time);
    }
    public void render(double cameraX, double cameraY){
        for (Bullet bullet : this) bullet.render(cameraX,cameraY);
    }
    public void shoot(Hero hero){
        if (findFirstThatCanBeShot() != null){
            findFirstThatCanBeShot().shoot(hero);
        }
    }
    public Bullet findFirstThatCanBeShot(){
        for (Bullet bullet : this){
            if (bullet.getTimeUntilCanBeShot() == 0){
                return bullet;
            }
        }
        return null;
    }
    public double minTimeUntilCanBeShotNonZero(){
        double tempMin = FIRE_RATE;
        for (Bullet bullet : this){
            if (bullet.getTimeUntilCanBeShot() < tempMin && bullet.getTimeUntilCanBeShot() != 0){
                tempMin = bullet.getTimeUntilCanBeShot();
            }
        }
        return tempMin;
    }
    public int numberOfCanBeShot(){
        int out = 0;
        for (Bullet bullet : this){
            if (bullet.getTimeUntilCanBeShot() == 0){
                out++;
            }
        }
        return out;
    }
}
