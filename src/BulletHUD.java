import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BulletHUD {
    private ImageView bulletIcons;
    private BulletLoadingBar bulletLoadingBar;
    private BulletList listOfBullets;
    private Rectangle2D bulletIconsSpriteArea;
    public BulletHUD(Pane pane, BulletList listOfBullets, String fileNameBulletIcons,String fileNameBulletLoadingBar){
        this.listOfBullets = listOfBullets;
        bulletIcons = new ImageView(new Image(fileNameBulletIcons));
        pane.getChildren().add(bulletIcons);
        bulletLoadingBar = new BulletLoadingBar(pane,10,40,listOfBullets,fileNameBulletLoadingBar);
    }


    public void update(long time){
        bulletLoadingBar.update(time);
        bulletIconsSpriteArea = new Rectangle2D(0,12 * listOfBullets.numberOfCanBeShot(),38,12);

    }
    public void render(){
        bulletIcons.setX(60); bulletIcons.setY(38);
        bulletIcons.setViewport(bulletIconsSpriteArea);

        bulletLoadingBar.render();
    }
}
