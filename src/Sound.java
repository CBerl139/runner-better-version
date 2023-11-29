import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

public class Sound {
    private Dictionary<String, MediaPlayer> listOfSoundFiles;
    public Sound(){

        for(String fileName : new ArrayList<>(Arrays.asList(
                "Honking.wav",
                "Mars.wav",
                "Mercury.wav",
                "Venus.wav"
        ))){
            Media sound = new Media("file:./sound/" + fileName);
            MediaPlayer soundplayer = new MediaPlayer(sound);
            listOfSoundFiles.put(fileName,soundplayer); //Dictionary made of fileNames and their respective MediaPlayer
        }
    }
    public void playSound(String fileName){
        MediaPlayer player = this.listOfSoundFiles.get(fileName);
        player.play();
    }
}
