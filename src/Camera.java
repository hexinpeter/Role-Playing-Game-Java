/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Xin He <hex1@student.unimelb.edu.au>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{

    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenWidth = RPG.screenWidth;
    /** Screen height, in pixels. */
    public final int screenHeight = RPG.screenHeight - RPG.PANELHEIGHT;

    
    /** The camera's position in the world, in x and y coordinates. */
    private double xPos;
    private double yPos;

    
    public double getxPos() {
        // TO DO: Fill In
        return xPos;
    }

    public double getyPos() {
        // TO DO: Fill In
        return yPos;
    }

    
    /** Create a new Camera object. */
    public Camera(Player player, int screenWidth, int screenHeight)
    {   
        // TO DO: Fill In
    	unitFollow = player;
        xPos = unitFollow.getxPos();
        yPos = unitFollow.getyPos();
    }

    /** Update the game camera to recentre it's viewpoint around the player 
     */
    public void update()
    throws SlickException
    {
        // TO DO: Fill In
        xPos = unitFollow.getxPos();
        yPos = unitFollow.getyPos();
    }
    
    /** return the minimum x value on screen 
     */
    public int getMinX(){
        // TO DO: Fill In
        return (int)(xPos - screenWidth/2);
    }
    
    /** return the maximum x value on screen 
     */
    public int getMaxX(){
        // TO DO: Fill In
        return (int)(xPos + screenWidth/2);
    }
    
    /** return the minimum y value on screen 
     */
    public int getMinY(){
        // TO DO: Fill In
        return (int)(yPos - screenHeight/2);
    }
    
    /** return the maximum y value on screen 
     */
    public int getMaxY(){
        // TO DO: Fill In
        return (int)(yPos + screenHeight/2);
    }

    /** Tells the camera to follow a given unit. 
     */
    public void followUnit(Object unit)
    throws SlickException
    {
        // TO DO: Fill In
        unitFollow = (Player) unit;
        xPos = unitFollow.getxPos();
        yPos = unitFollow.getyPos();
    }
    
}