import javafx.scene.layout.Pane;

import java.io.File;

public class Hearts{
    private HeartsAnimation animation;
    private GameScene gameScene;
    public Hearts(Pane pane,GameScene gameScene, double x, double y){
        this.gameScene = gameScene;
        this.animation = new HeartsAnimation(this,x,y);
        pane.getChildren().add(animation);
    }
    public HeartsAnimation getAnimation(){
        return animation;
    }

    public void update(Hero hero, long time){  //calculate hearts position on screen
        animation.update(hero,time);
    }

    public void render(){       //change hearts position on screen
        animation.render();
    }
}
