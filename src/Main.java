import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements GlobalConstants{
    private StartPage startPage;
    private GamePage gamePage;
    private FinishPage finishPage;
    private Stage primaryStage;
    private double highScorePercentage;
    public static Sound sound;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //sound = new Sound();

        this.primaryStage = primaryStage;
        primaryStage.setTitle("TD PROJECT : RUNNER");
        primaryStage.setResizable(false);

        //display the start menu :
        startMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void finishGame(int numberOfHearts, double lastScore){
        gamePage.stopTimer();

        finishPage = new FinishPage(this,numberOfHearts,lastScore);
        primaryStage.setScene(finishPage.getScene());

        primaryStage.show();
    }
    public void startGame(){
        gamePage = new GamePage(this);
        primaryStage.setScene(gamePage.getGameScene());

        primaryStage.show();
    }
    public void startMenu(){
        startPage = new StartPage(this);
        primaryStage.setScene(startPage.getScene());

        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public double getHighScore(){
        return highScorePercentage;
    }
    public void setHighScore(double highScorePercentage){
        this.highScorePercentage = highScorePercentage;
    }
}
