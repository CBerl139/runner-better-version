import javafx.animation.AnimationTimer;
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
    private Dictionary<UserInput,Boolean> inputPressed;
    private UserInput keyPressed;
    private AnimationTimer timer;
    private DevHUD devHud;
    private WinFlag winFlag;
    private Boolean timerIsRunning; //Boolean to pause or unpause the game
    public GameScene(Main parentMain, Pane parentPane, double v, double v1, boolean b) {
        super(parentPane, v, v1, b);
        this.parentMain = parentMain;
        //TODO : add birds at the top of the screen that go from right to left and fly away if too close to the hero

        //background = new BackgroundAnimation(parentPane,"file:./img/desert.png");
        background = new ParalaxBackgroundAnimation(parentPane);
        //TODO : get better sprites for the foes
        listOfFoes = new FoeList(parentPane,FOE_DENSITY,"file:./img/heros.png");

        hero = new Hero(parentPane,HERO_STARTING_POSITION_X,HERO_STARTING_POSITION_Y,6,85,
                104,59, "file:./img/heros.png");

        listOfBullets = new BulletList(parentPane,hero,"file:./img/heros.png");

        camera = new Camera(HERO_STARTING_POSITION_X, HERO_STARTING_POSITION_Y);

        hearts = new Hearts(parentPane,this,10,20);

        bulletHUD = new BulletHUD(parentPane,listOfBullets, "file:./img/bullet-icons.png",
                                                        "file:./img/bullet-loading-bar.png");
        winFlag = new WinFlag(parentPane,this,FINISH_LINE_X,-50,"file:./img/win-flag.png");

        progressHUD = new ProgressHUD(parentPane, parentMain,"file:./img/progress-bar.png");

        devHud = new DevHUD(parentPane,hero,listOfFoes,listOfBullets,winFlag);

        InputManager inputManager = new InputManager(this,hero,listOfBullets,devHud);
        //inputPressed = new Hashtable<>();
        //InputManager inputManager = new InputManager(this,inputPressed);

        timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                update(time);
                render();
            }
        };
        timer.start();
        timerIsRunning = true;
    }
    public void update(long time){
        //move things that move and compute other things (e.g. hitbox collision, etc.)
        hero.update(inputPressed,time);
        hearts.update(hero,time);
        listOfFoes.update(hero,time);
        camera.update(hero,time);
        devHud.update(time);
        listOfBullets.update(listOfFoes,hero,time);
        bulletHUD.update(time);
        devHud.update(time);
        progressHUD.update(hero,time);
        winFlag.update(hero,time);
        //finish game is player has no more hearts
        if (hero.getNumberOfHearts() == 0){
            this.finishGame(hero.getNumberOfHearts());
        }
        //"win flag gets captured" win condition is handled in WinFlag class
    }
    public void render(){
        //render the graphics of everything that is displayed on screen
        hero.render(camera.getX(),camera.getY());
        listOfFoes.render(camera.getX(), camera.getY());
        background.render(camera.getX(),camera.getY());
        devHud.render(camera.getX(),camera.getY());
        listOfBullets.render(camera.getX(),camera.getY());
        progressHUD.render();
        winFlag.render(camera.getX(),camera.getY());
        hearts.render();
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
        if (timerIsRunning) {
            timer.stop();
        }else {
            timer.start();
        }
        timerIsRunning = !timerIsRunning;
    }
}
