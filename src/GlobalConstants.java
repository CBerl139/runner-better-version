
public interface GlobalConstants {
    double HERO_STARTING_POSITION_X = 0;
    double HERO_STARTING_POSITION_Y = 0;
    double WINDOW_WIDTH = 800;
    double WINDOW_HEIGHT = 400;
    double BACKGROUND_WIDTH = 800;
    double BACKGROUND_HEIGHT = 400;
    double HERO_SPEED_X = 5;
    double BULLET_SPEED = 5;
    double JUMP_HEIGHT = 5;
    double XOFFSET = WINDOW_WIDTH / 2 / 2;
    //Offset the displayed elements to adjust to the size of the window and make sure the hero is always at the bottom
    double YOFFSET = - (BACKGROUND_HEIGHT - WINDOW_HEIGHT);
    int FOE_DENSITY = 3;
    double INVINCIBILITY_DURATION = 2.5 * 100;
    int MAX_NUMBER_OF_JUMPS = 2;
    double FINISH_LINE_X = 40000;
    double FIRE_RATE = 6 * 100;

}
