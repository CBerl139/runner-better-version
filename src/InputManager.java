import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Dictionary;

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
}
