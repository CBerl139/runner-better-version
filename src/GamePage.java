import javafx.scene.Group;
import javafx.scene.layout.Pane;


public class GamePage implements GlobalConstants{
    private GameScene gameScene;
    private Main parentMain;

    public GamePage(Main parentMain){
        this.parentMain = parentMain;

        Group root = new Group();
        Pane pane = new Pane(root);

        //Main.sound.playSound("file:./sound/Mars.wav");

        gameScene = new GameScene(parentMain,pane, WINDOW_WIDTH, WINDOW_HEIGHT, true);
    }
    public GameScene getGameScene(){
        return this.gameScene;
    }
    public void stopTimer(){
        gameScene.toggleTimer();
    }
}
