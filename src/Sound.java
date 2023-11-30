import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

public class Sound {
    //doesn't work
    public Sound(){

    }
    public void playSound(String fileName){
        MediaPlayer player = new MediaPlayer(new Media(fileName));
        player.play();
    }
}
