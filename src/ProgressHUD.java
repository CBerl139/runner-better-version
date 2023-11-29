import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ProgressHUD implements GlobalConstants{
    private ProgressBar progressBar;
    private Text score;
    private Text highScore;
    private double scorePercentage;
    private Main parentMain;
    public ProgressHUD(Pane pane, Main parentMain, String fileNameProgressBar){
        this.parentMain = parentMain;

        progressBar = new ProgressBar(pane,(WINDOW_WIDTH - 388 - 100) / 2,10,fileNameProgressBar);
        scorePercentage = 0;

        Font font = new Font("Liberation Mono", 16);
        score = new Text(WINDOW_WIDTH - 200 - 50,22,  scorePercentage + "%");
        score.setFont(font);

        highScore = new Text(WINDOW_WIDTH - 180,22,  "High Score : " + parentMain.getHighScore() + "%");
        highScore.setFont(font);

        pane.getChildren().add(score);
        pane.getChildren().add(highScore);
    }
    public void update(Hero hero, long time){
        scorePercentage = Math.floor(1000 * hero.getX() / FINISH_LINE_X) / 10;
        progressBar.update(hero,time);

        //update high score if it is reached
        if (scorePercentage > parentMain.getHighScore()){
            parentMain.setHighScore(scorePercentage);
        }
    }
    public void render(){
        progressBar.render();

        score.setText(scorePercentage + "%");
        highScore.setText("High Score : " + parentMain.getHighScore() + "%");
    }
    public double getScorePercentage(){
        return scorePercentage;
    }
}
