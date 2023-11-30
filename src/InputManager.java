import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputManager {
    public InputManager(GameScene gameScene, Hero hero, BulletList listOfBullets, DevHUD devHUD){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case SPACE:
                        hero.jump();
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
