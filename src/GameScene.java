import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.Dictionary;
import java.util.Hashtable;

public class GameScene extends Scene implements GlobalConstants{
    private Main parentMain;
    private Camera camera;
    private ParalaxBackgroundAnimation background;
    private Hero hero;
    private FoeList listOfFoes;
    private BulletList listOfBullets;
    private Hearts hearts;
    private BulletHUD bulletHUD;
    private ProgressHUD progressHUD;
    private AnimationTimer timer;
    private DevHUD devHud;
    private WinFlag winFlag;
    private Boolean timerIsRunning; //Boolean to pause or unpause the game
    public GameScene(Main parentMain, Pane parentPane, double v, double v1, boolean b) {
        super(parentPane, v, v1, b);
        this.parentMain = parentMain;
        //TODO : add birds at the top of the screen that go from right to left and fly away if too close to the hero
        //create the background without parallax effect
        //background = new BackgroundAnimation(parentPane,"file:./img/desert.png");
        //create the background with parallax effect :
        background = new ParalaxBackgroundAnimation(parentPane);
        //TODO : get better sprites for the foes
        listOfFoes = new FoeList(parentPane,FOE_DENSITY,"file:./img/heros.png");
        //create the hero
        hero = new Hero(parentPane,HERO_STARTING_POSITION_X,HERO_STARTING_POSITION_Y,6,85,
                104,59, "file:./img/heros.png");
        //create the list of bullets (3 bullets)
        listOfBullets = new BulletList(parentPane,hero,"file:./img/heros.png");
        //create the camera and place it where the hero starts
        camera = new Camera(HERO_STARTING_POSITION_X, HERO_STARTING_POSITION_Y);
        //create the heart display at the top left
        hearts = new Hearts(parentPane,this,10,20);
        //create the bullet icons and loading bar at the top left
        bulletHUD = new BulletHUD(parentPane,listOfBullets, "file:./img/bullet-icons.png",
                                                        "file:./img/bullet-loading-bar.png");
        //create the win flag at the end of the game
        winFlag = new WinFlag(parentPane,this,FINISH_LINE_X,-50,"file:./img/win-flag.png");
        //create the progress bar and score text at the top of the screen
        progressHUD = new ProgressHUD(parentPane, parentMain,"file:./img/progress-bar.png");
        //create the devHUD which displays informations about the game when pressing K
        devHud = new DevHUD(parentPane,hero,listOfFoes,listOfBullets,winFlag);
        //manage keyboard inputs :
        InputManager inputManager = new InputManager(this,hero,listOfBullets,devHud);
        //create timer and make it call update and render every frame
        timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                update(time);
                render();
            }
        };
        //start timer
        timer.start();
        timerIsRunning = true;
    }
    public void update(long time){
        //move things that move and compute other things (e.g. hitbox collision, etc.)

        //move hero according to gravity
        hero.update(time);
        //update the number of hearts to be displayed based on the player's number of heart
        hearts.update(hero,time);
        //move foes and check if they are in collision with hero
        listOfFoes.update(hero,time);
        //move camera with spring effect around the hero (if window height allows it)
        camera.update(hero,time);
        //compute number of FPS to be displayed on the devHUD
        devHud.update(time);
        //move bullets and test if any bullet is in collision with any foe
        listOfBullets.update(listOfFoes,hero,time);
        //update bullet loading bar percentage and icons based on number of bullets that can be shot
        bulletHUD.update(time);
        //compute player's percentage and update high score if it is reached
        progressHUD.update(hero,time);
        //test if player is in contact with win flag and finish game if so
        winFlag.update(hero,time);
        //finish game is player has no more hearts
        if (hero.getNumberOfHearts() == 0){
            this.finishGame(hero.getNumberOfHearts());
        }
        //"win flag gets captured" win condition is handled in WinFlag class
    }
    public void render(){
        //render the graphics of everything that is displayed on screen

        //update player position on screen and sprite
        hero.render(camera.getX(),camera.getY());
        //update foes position on screen and sprite
        listOfFoes.render(camera.getX(), camera.getY());
        //update background position on screen
        background.render(camera.getX(),camera.getY());
        //update info text and hitboxes positions on screen
        devHud.render(camera.getX(),camera.getY());
        //update bullets position on screen
        listOfBullets.render(camera.getX(),camera.getY());
        //update win flag position on screen
        winFlag.render(camera.getX(),camera.getY());
        //update progress bar sprite and percentage text
        progressHUD.render();
        //update hearts sprite
        hearts.render();
        //update bullet loading bar and icons' sprites
        bulletHUD.render();
    }
    public void finishGame(int numberOfHearts){
        parentMain.finishGame(numberOfHearts,progressHUD.getScorePercentage());
    }
    public void restartGame(){
        timer.stop();
        parentMain.startGame();
    }
    public void stopTimer(){
        timer.stop();
    }
    public void toggleTimer(){
        //pause or unpause timer
        if (timerIsRunning) {
            timer.stop();
        }else {
            timer.start();
        }
        timerIsRunning = !timerIsRunning;
    }
}
