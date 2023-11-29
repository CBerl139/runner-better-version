import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class FoeList extends ArrayList<Foe>{
    //TODO : change the spacing of the foes to make it better
    public FoeList(Pane pane, int foeDensity,String fileName){
        super();
        for (int i = 0; i < foeDensity; i++){
            this.add(new Foe(pane,1200 + (i * 1000) + (350 * Math.random()),0,6,
                    85,104,59,fileName));
        }
    }
    public void update(Hero hero, long time){
        for (Foe foe : this){
            foe.update(hero,time);
        }
    }
    public void render(double cameraX, double cameraY){
        for (Foe foe : this){
            foe.render(cameraX, cameraY);
        }
    }
}
