import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartPage {
    private Scene scene;
    private Main parentMain;
    public StartPage(Main parentMain){
        this.parentMain = parentMain;
        Group root = new Group();
        Pane pane = new Pane(root);

        scene = new Scene(pane,400,400,true);
        ImageView background = new ImageView(new Image("file:./img/desert.png"));
        pane.getChildren().add(background);
        background.relocate(-200,0);

        //create welcome text
        Text gameTitle = new Text(100,200,"TD PROJECT : RUNNER" +
                                                    "\nSPACE : Jump" +
                                                    "\nR : Shoot");
        gameTitle.setFont(new Font("Arial",20));
        gameTitle.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(gameTitle);
        //create credits text
        Text credits = new Text(400 - 155, 400 - 10, "Made by : CBerl139");
        pane.getChildren().add(credits);

        //create start button
        Button playButton = new Button("Start !");
        pane.getChildren().add(playButton);
        playButton.setMinSize(50,50);
        playButton.relocate(175,275);

        //button action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                startGame();
            }
        };

        playButton.setOnAction(event);
    }
    public Scene getScene(){
        return scene;
    }
    public void startGame(){
            parentMain.startGame();
    }
}
