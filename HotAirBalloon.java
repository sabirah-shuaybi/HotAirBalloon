import java.awt.*;
import objectdraw.*;
import java.util.Random;
/**
 * HotAirBalloon class is responsible for defining a blueprint for a hot air balloon
 * This requires structuring, positioning and creating each of the individual parts relative to mouse click
 * Defines a randomColor, contains, moveVertical, darken and brighten methods for all instances of HotAirBalloon
 *
 * @author Sabirah Shuaybi
 * @version 9/27/16
 */
public class HotAirBalloon
{
    //Various components needed to construct a hot air balloon
    private FilledOval balloon;
    private FilledRect basket;
    private Line leftRope;
    private Line rightRope;
    private FilledRect leftSandbag;
    private FilledRect rightSandbag;
    private Text MHC;

    private static final int BALLOON_SIZE = 70;
    private static final int BASKET_SIZE = 20;
    private static final int BASKET_DISTANCE = 100;
    private static final int SANDBAG_WIDTH = 2;
    private static final int SANDBAG_HEIGHT = 10;

    /* Constructor for HotAirBalloon class */
    public HotAirBalloon (DrawingCanvas canvas, Location location) {

        //Dynamically set x and y coordinates of basket location
        double basketX = location.getX() - BASKET_SIZE/2;
        double basketY = location.getY() + BASKET_DISTANCE;

        basket = new FilledRect(basketX, basketY, BASKET_SIZE, BASKET_SIZE, canvas);

        //Set x and y coordinates of left most sandbag relative to basket
        double leftSandbagX = basketX + BASKET_SIZE/4;
        double leftSandbagY = basketY;

        leftSandbag = new FilledRect(leftSandbagX, leftSandbagY, SANDBAG_WIDTH, SANDBAG_HEIGHT, canvas);
        //Set color of sandbag to yellow
        leftSandbag.setColor(new Color(255, 255, 0));

        //Set x and y coordinates of right sandbag relative to basket
        double rightSandbagX = basketX + BASKET_SIZE - 7;
        double rightSandbagY = basketY;

        rightSandbag = new FilledRect(rightSandbagX, rightSandbagY, SANDBAG_WIDTH, SANDBAG_HEIGHT, canvas);
        //Set color of sandbag to yellow
        rightSandbag.setColor(new Color(255, 255, 0));

        //Set x and y coordinates for left rope start point
        double leftStartX = location.getX() - BALLOON_SIZE/2;
        double leftStartY = location.getY();
        //Set x and y coordinates for left rope end point
        double leftEndX = basketX;
        double leftEndY = basketY;

        leftRope = new Line(leftStartX, leftStartY, leftEndX, leftEndY, canvas);

        //Set x and y coordinates for right rope start point
        double rightStartX = location.getX() + BALLOON_SIZE/2;
        double rightStartY = location.getY();
        //Set x and y coordinates for right rope end point
        double rightEndX = basketX + BASKET_SIZE;
        double rightEndY = basketY;

        rightRope =  new Line(rightStartX, rightStartY, rightEndX, rightEndY, canvas);

        //Dynamically set x and y coordinates of balloon (determined by user mouse click)
        double balloonX = (location.getX() - (BALLOON_SIZE/2));
        double balloonY = (location.getY() - (BALLOON_SIZE/2));
        //Constructor for balloon
        balloon = new FilledOval(balloonX, balloonY, BALLOON_SIZE, BALLOON_SIZE, canvas);
        balloon.setColor(randomColor());

        //Dynamically set x and y coordinates for MHC banner
        double MHCX = location.getX() - BALLOON_SIZE/2 + 8;
        double MHCY = location.getY() - 13;
        MHC = new Text("MHC", MHCX, MHCY, canvas);

        //Set MHC text color to white
        MHC.setColor(new Color(255, 255, 255));
        //Get font size to print on terminal in order to then adjust font size
        System.out.println("MHC Font Size" + MHC.getFont());
        //Set MHC text font size to 23 (was originally 13)
        int fontSize = 23;
        MHC.setFontSize(fontSize);
    }

    /* Determines if mouse click is contained within any part of hot air balloon */
    public boolean contains(Location mouseClick) {

        if (balloon.contains(mouseClick))
            return true;
        if (basket.contains(mouseClick))
            return true;
        if (leftRope.contains(mouseClick))
            return true;
        if (rightRope.contains(mouseClick))
            return true;

        return false;

    }

    /* Creates and returns a purely random color */
    public Color randomColor() {

        //Randomly assign value ranging from 0-255 to each RGB component 
        Random r = new Random();
        int red = r.nextInt(255);
        int green = r.nextInt(255);
        int blue = r.nextInt(255);
        
        //Set balloon to a random color 
        return new Color(red, green, blue);
    }

    /* Moves entire hot air balloon up and down */
    public void moveVertical(double dy) {

        //Move each hot air balloon part vertically only
        balloon.move(0, dy);
        basket.move(0, dy);
        leftRope.move(0, dy);
        rightRope.move(0, dy);
        leftSandbag.move(0, dy);
        rightSandbag.move(0, dy);
        MHC.move(0,dy);

    }

    /* Darkens color of last created balloon */
    public void darker() {

        balloon.setColor(balloon.getColor().darker());
    }

    /* Brightens color of last created balloon */
    public void brighter() {

        balloon.setColor(balloon.getColor().brighter());
    }

}
