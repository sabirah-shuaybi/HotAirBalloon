import java.awt.*;
import objectdraw.*;

/**
 * This program presents a hot air balloon when it starts and allows the user to rise and fall the balloon
 * Creates a new hot air balloon each time user clicks on screen at that location point
 * The color of each new hot air balloon clicked on to the screen is determined randomly
 * Allows user to vertically drag only the last balloon created.
 *
 * @author Sabirah Shuaybi
 * @version 9/27/16
 */
public class BalloonController extends WindowController
{

    //sky and grass
    private FilledRect sky;
    private FilledRect grass;

    //Constants for sky
    private static final int SKY_LEFT = 0;
    private static final int SKY_TOP = 0;
    private static final int SKY_WIDTH = 400;
    private static final int SKY_HEIGHT = 400;

    //Constants for grass
    private static final int GRASS_LEFT = 0;
    private static final int GRASS_TOP = 310;
    private static final int GRASS_WIDTH = 400;
    private static final int GRASS_HEIGHT = 100;

    private HotAirBalloon hotAirBalloon;

    //To hold the location of the point where mouse was last seen, for dragging purposes
    private Location lastPoint;

    //To keep track if hot air balloon is grabbed by user
    private boolean hotAirBalloonGrabbed = false;

    /* Creates the backdrop consisting of sky and grass and creates one hot air balloon */
    public void begin() {

        //Constructor for sky
        sky = new FilledRect(SKY_LEFT, SKY_TOP, SKY_WIDTH, SKY_HEIGHT, canvas);
        //Make the sky blue
        Color skyColor = new Color(0, 0, 255);
        sky.setColor(skyColor);

        //Constructor for grass
        grass = new FilledRect(GRASS_LEFT, GRASS_TOP, GRASS_WIDTH, GRASS_HEIGHT, canvas);
        //Make the grass green
        Color grassColor = new Color(0, 255, 0);
        grass.setColor(grassColor);

        //Construct an instance of a HotAirBalloon at start of program
        hotAirBalloon = new HotAirBalloon(canvas, new Location(90,100));

    }

    /* Evaluates whether or not click was contained within last created hot air balloon and proceeds accordingly */
    public void onMousePress(Location currentPoint) {

        //If hot air balloon clicked on, set boolean flag to true
        if (hotAirBalloon.contains(currentPoint) == true) {
            hotAirBalloonGrabbed = true;}
        //If click is not contained in hot air balloon, create a new hot air balloon at location of click
        else {
            hotAirBalloon = new HotAirBalloon(canvas, currentPoint);
        }

        //Capture last location of mouse for onMouseDrag method
        lastPoint = currentPoint;
    }

    //This method moves the last created hot air balloon a vertical distance with equals the mouse drag distance
    public void onMouseDrag(Location currentPoint) {

        double verticalDistance = currentPoint.getY() - lastPoint.getY();
        //Move balloon up and down if grabbed
        if (hotAirBalloonGrabbed) {
            hotAirBalloon.moveVertical(verticalDistance);
        }

        lastPoint = currentPoint;
    }

    /* Sets hotAirBalloonGrabbed to false to prevent movement of hot air balloon if it has not been grabbed */
    public void onMouseRelease(Location currentPoint) {

        hotAirBalloonGrabbed = false;
    }

    /* Brightens last created hot air balloon, sky, and grass when mouse enters window */
    public void onMouseEnter(Location p) {

        //Brighten hot air balloon
        hotAirBalloon.brighter();

        //For debugging purposes (to figure out if program recognizes that mouse has entered the frame)
        System.out.println("MouseEntered");

        //Brighten color of sky and grass
        sky.setColor(sky.getColor().brighter());
        grass.setColor(grass.getColor().brighter());
    }

    /* Darkens last created hot air balloon, sky, and grass when mouse exits window */
    public void onMouseExit(Location p) {

        //Darken hot air balloon
        hotAirBalloon.darker();
        //Debugging
        System.out.println("MouseExited");

        //Darken color of sky and grass
        sky.setColor(sky.getColor().darker());
        grass.setColor(grass.getColor().darker());

    }

}

