import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FinishPage {
    private Main parentMain;
    private Scene scene;
    public FinishPage(Main parentMain, int numberOfHearts, double lastScore){
        this.parentMain = parentMain;
        Group root = new Group();
        Pane pane = new Pane(root);

        scene = new Scene(pane,500,500,true);
        pane.getChildren().add(new ImageView(new Image(numberOfHearts!=0?"file:./img/win-screen.png":"file:./img/game-over-screen.png")));

        Text text = new Text(175,175,numberOfHearts!=0?("YOU WON !!!\n Hearts Remaining : "):"YOU LOST :/");
        pane.getChildren().add(text);

        Text scoreInfo = new Text(175,225,"");
        pane.getChildren().add(scoreInfo);
        scoreInfo.setTextAlignment(TextAlignment.RIGHT);

        if (numberOfHearts == 0){
            scoreInfo.setText("Score : " + lastScore + "%" + "\nHigh Score : " + parentMain.getHighScore() + "%");
        } else {
            ImageView imageView = new ImageView(new Image("file:./img/heart-spritesheet.png"));
            imageView.setViewport(new Rectangle2D(0,12 * numberOfHearts,36,12));
            imageView.setX(200);
            imageView.setY(200);
            pane.getChildren().add(imageView);

            scoreInfo.setText("High Score : " + parentMain.getHighScore() + "%");
        }

        Button restartButton = new Button("Restart Game !");
        pane.getChildren().add(restartButton);
        restartButton.setMinSize(100,100);
        restartButton.relocate(175,250);

        //action when button is pressed
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                startMenu();
            }
        };
        restartButton.setOnAction(event);
    }
    public Scene getScene(){
        return this.scene;
    }
    public void startMenu(){
        parentMain.startMenu();
    }
}
