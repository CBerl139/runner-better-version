import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Dictionary;

enum UserInput{
    SPACE,E,R
}
public class InputManager {
    Hero hero;
    public InputManager(GameScene gameScene, Hero hero, BulletList listOfBullets, DevHUD devHUD){
        this.hero = hero;
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case SPACE:
                        hero.jump();
                        break;
                    case E:
                        hero.toggleBehavior();  //useless, behavior is automatically set correctly by the hero.update() method
                        break;
                    case R:
                        hero.shoot(listOfBullets);
                        break;
                    case K:
                        devHUD.toggleDevHud();
                        break;
                    case P:
                        gameScene.toggleTimer();
                        break;
                    case Q:
                        gameScene.restartGame();
                        break;
                    case I:
                        hero.toggleGodMode();
                }
            }
        });

    }
    public InputManager(GameScene gameScene, Dictionary<UserInput, Boolean> inputPressed,Hero hero){
        this.hero = hero;
        inputPressed.put(UserInput.SPACE,false);
        inputPressed.put(UserInput.E,false);
        inputPressed.put(UserInput.R,false);

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case SPACE -> inputPressed.put(UserInput.SPACE,true);
                    case E -> inputPressed.put(UserInput.E,true);
                    case R -> inputPressed.put(UserInput.R,true);
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case SPACE -> inputPressed.put(UserInput.SPACE,false);
                    case E -> inputPressed.put(UserInput.E,false);
                    case R -> inputPressed.put(UserInput.R,false);
                }
            }
        });
    }
}
