import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Dictionary;
import java.util.Hashtable;

//Displays information about the game on screen and shows hitboxes
//Can be accessed by pressing K when in game
public class DevHUD implements GlobalConstants{
    private Text heroCoordsText;
    private Text invincibilityText;
    private Text controlsText;
    private Text heartsNumberText;
    private Text bulletTimeUntilCanGetShotText;
    private Text godModeOn;
    private Text devModeOn;
    private Text fpsText;
    private Hero hero;
    private FoeList listOfFoes;
    private BulletList listOfBullets;
    private WinFlag winFlag;
    private Boolean isVisible;
    private Rectangle heroHitBox;
    private Dictionary<Foe,Rectangle> listOfFoeHitBoxes;
    private Dictionary<Bullet,Rectangle> listOfBulletHitBoxes;
    private Rectangle winFlagHitBox;
    private int accumulatedFrames;
    private int lastHalfSecond;
    private int FPS;
    public DevHUD(Pane pane, Hero hero, FoeList listOfFoes, BulletList listOfBullets, WinFlag winFlag){
        this.hero = hero;
        this.listOfFoes = listOfFoes;
        this.listOfBullets = listOfBullets;
        this.winFlag = winFlag;
        this.isVisible = false;     //is visible by default or no


        heroCoordsText = new Text(20,80,"x: " + hero.getX() + "\tobjective: " + FINISH_LINE_X + "\ny: " + hero.getY());
        Font font = new Font("Liberation Mono", 20);
        heroCoordsText.setFont(font);
        heroCoordsText.setFill(Color.BLACK);

        invincibilityText = new Text(20,130,"invincibility:" + hero.getInvincibility());
        invincibilityText.setFont(font);
        invincibilityText.setFill(Color.BLACK);

        controlsText = new Text(570,40, "K: Show this menu" +
                                        "\nP: Pause the timer" +
                                        "\nQ: Restart the game" +
                                        "\nI: Turn on GodMode" +
                                        "\n\nSPACE: Jump" +
                                        "\nR: Shoot");
        controlsText.setFont(font);
        controlsText.setFill(Color.BLACK);

        heartsNumberText = new Text(110,20,"numberOfHearts: " + hero.getNumberOfHearts());
        heartsNumberText.setFont(font);
        heartsNumberText.setFill(Color.BLACK);

        bulletTimeUntilCanGetShotText = new Text(110,50,"timeUntilCanGetShot: " + listOfBullets.minTimeUntilCanBeShotNonZero());
        bulletTimeUntilCanGetShotText.setFont(font);
        bulletTimeUntilCanGetShotText.setFill(Color.BLACK);

        godModeOn = new Text(WINDOW_WIDTH - 150,WINDOW_HEIGHT - 20,"God Mode ON");
        godModeOn.setFont(font);
        godModeOn.setFill(Color.BLACK);

        devModeOn = new Text(WINDOW_WIDTH - 222  ,WINDOW_HEIGHT - 40,"Developer Mode ON");
        devModeOn.setFont(font);
        devModeOn.setFill(Color.BLACK);

        fpsText = new Text(20,WINDOW_HEIGHT - 20,"FPS:");
        fpsText.setFont(font);
        fpsText.setFill(Color.BLACK);

        listOfBulletHitBoxes = new Hashtable<>();
        listOfFoeHitBoxes = new Hashtable<>();
        heroHitBox = new Rectangle(hero.getHitBox().getMinX(),hero.getHitBox().getMinY(),hero.getHitBox().getWidth(),hero.getHitBox().getHeight());
        pane.getChildren().add(heroHitBox);
        heroHitBox.setFill(Color.BLUE);
        for (Foe foe : listOfFoes) {
            Rectangle hitBox = new Rectangle(foe.getHitBox().getMinX(),foe.getHitBox().getMinY(),foe.getHitBox().getWidth(),foe.getHitBox().getHeight());
            listOfFoeHitBoxes.put(foe,hitBox);
            hitBox.setStrokeWidth(8);
            hitBox.setFill(Color.RED);
            pane.getChildren().add(hitBox);
        }
        for (Bullet bullet : listOfBullets){
            Rectangle hitBox = new Rectangle(bullet.getHitBox().getMinX(),bullet.getHitBox().getMinY(),bullet.getHitBox().getWidth(),bullet.getHitBox().getHeight());
            listOfBulletHitBoxes.put(bullet,hitBox);
            pane.getChildren().add(hitBox);
        }

        winFlagHitBox = new Rectangle(winFlag.getHitBox().getMinX(),winFlag.getHitBox().getMinY(),winFlag.getHitBox().getWidth(),winFlag.getHitBox().getHeight());
        winFlagHitBox.setFill(Color.GREEN);
        pane.getChildren().add(winFlagHitBox);

        pane.getChildren().add(heroCoordsText);
        pane.getChildren().add(invincibilityText);
        pane.getChildren().add(controlsText);
        pane.getChildren().add(heartsNumberText);
        pane.getChildren().add(bulletTimeUntilCanGetShotText);
        pane.getChildren().add(godModeOn);
        pane.getChildren().add(devModeOn);
        pane.getChildren().add(fpsText);

    }

    public void update(long time){
        accumulatedFrames++;
        int currentHalfSecond = (int) Math.floor(2 * time/1000000000);
        if (currentHalfSecond > lastHalfSecond) {   //update the count every half second
            FPS = 2 * accumulatedFrames;
            accumulatedFrames = 0;                  //reset accumulatedFrames
            lastHalfSecond = currentHalfSecond;
        }
    }
    public void render(double cameraX, double cameraY){
        //change hero hitbox color based on invincibility and godmode
        if (hero.getGodMode()){
            heroHitBox.setFill(Color.YELLOW);
        }else if (hero.getInvincibility() != 0){
            heroHitBox.setFill(Color.CYAN);
        }else{
            heroHitBox.setFill(Color.BLUE);
        }
        //move hero hitbox rectangle
        heroHitBox.setVisible(isVisible);
        heroHitBox.setX(hero.getHitBox().getMinX() - cameraX);
        heroHitBox.setY(250 + YOFFSET + hero.getHitBox().getMinY() - cameraY);
        //move foes hitbox rectangle
        for (Foe foe : listOfFoes){
            listOfFoeHitBoxes.get(foe).setVisible(isVisible);
            listOfFoeHitBoxes.get(foe).setX(foe.getHitBox().getMinX() - cameraX);
            listOfFoeHitBoxes.get(foe).setY(250 + YOFFSET + foe.getHitBox().getMinY() - cameraY);
        }
        //move bullet hitbox rectangle
        for (Bullet bullet : listOfBullets){
            listOfBulletHitBoxes.get(bullet).setVisible(isVisible);
            listOfBulletHitBoxes.get(bullet).setX(bullet.getHitBox().getMinX() - cameraX);
            listOfBulletHitBoxes.get(bullet).setY(250 + YOFFSET + bullet.getHitBox().getMinY() - cameraY);
        }

        winFlagHitBox.setVisible(isVisible);
        winFlagHitBox.setX(0 + (winFlag.getX() - cameraX));
        winFlagHitBox.setY(250 + YOFFSET + (winFlag.getY() - cameraY));

        heroCoordsText.setVisible(isVisible);
        heroCoordsText.setText("x: " + hero.getX() + "\tobjective: " + FINISH_LINE_X + "\ny: " + hero.getY());

        invincibilityText.setVisible(isVisible);
        invincibilityText.setText("invincibility:" + hero.getInvincibility()   );

        controlsText.setVisible(isVisible);

        heartsNumberText.setVisible(isVisible);
        heartsNumberText.setText("numberOfHearts: " + hero.getNumberOfHearts());

        bulletTimeUntilCanGetShotText.setVisible(isVisible);
        bulletTimeUntilCanGetShotText.setText("timeUntilCanGetShot: " + ((listOfBullets.minTimeUntilCanBeShotNonZero()!=FIRE_RATE)?listOfBullets.minTimeUntilCanBeShotNonZero():0.0));

        godModeOn.setVisible(hero.getGodMode());
        devModeOn.setVisible(isVisible);

        fpsText.setVisible(isVisible);
        fpsText.setText("FPS: " + FPS);
    }
    public void toggleDevHud(){
        isVisible = !isVisible;
    }
}
