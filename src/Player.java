import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class Player
{
	/** The player's position in the world, in x and y coordinates, in pixels. */
    private int xPos;
    private int yPos;

    // * The player's world position before the update in this current frame. 
    // private int previousXPos;
    // private int previousYPos;

    /** The player's image. */
    public Image image;
    private final int START_XPOS = 700;
    private final int START_YPOS = 700;
    private final float SPEED = 0.5f;

	/** Create a new player object.
	 * @param imageLocation The location of the player's image.
	 */
	public Player(String imageLocation) throws 
	 SlickException{
		xPos = START_XPOS;
		yPos = START_YPOS;
		image = new Image(imageLocation);
	}
    
    public int getxPos() {
        // TO DO: Fill In
        return xPos;
    }

    public int getyPos() {
        // TO DO: Fill In
        return yPos;
    }

    /** Update the player's position.
     * @param xMovement the units of movement on x axis.
     * @param yMovement the units of movement on y axis.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int xMovement, int yMovement, int delta, TiledMap map) {
        //System.out.println("x: " + xMovement + "y: " + yMovement);
        int xPosTest = (int) (xPos + xMovement * SPEED * delta);
        int yPosTest = (int) (yPos + yMovement * SPEED * delta);

        if (map.getTileProperty(curTileID(xPosTest, yPos, map), "block", "0") == "0")
            xPos = xPosTest;
        if (map.getTileProperty(curTileID(xPos, yPosTest, map), "block", "0") == "0")
            yPos = yPosTest;

     //    previousXPos = xPos;
     //    previousYPos = yPos;
    	// xPos += xMovement * SPEED * delta;
    	// yPos += yMovement * SPEED * delta;
    }

    // /** Revert the update action in this current frame. 
    //  */
    // public void cancelUpdate() {
    //     xPos = previousXPos;
    //     yPos = previousYPos;
    // }

    // /** Return the world xPos of player if it moves 1 unit to left.
    //  * @param delta Time passed since last frame (milliseconds).
    //  */
    // public int xPosIfMoveLeft(int delta) {
    //     return xPos += -1 * SPEED * delta;
    // }

    // /** Return the world xPos of player if it moves 1 unit to right.
    //  * @param delta Time passed since last frame (milliseconds).
    //  */
    // public int xPosIfMoveRight(int delta) {
    //     return xPos += 1 * SPEED * delta;
    // }

    // /** Return the world xPos of player if it moves 1 unit upwards.
    //  * @param delta Time passed since last frame (milliseconds).
    //  */
    // public int yPosIfMoveUp(int delta) {
    //     return yPos += -1 * SPEED * delta;
    // }

    // /** Return the world xPos of player if it moves 1 unit downwards.
    //  * @param delta Time passed since last frame (milliseconds).
    //  */
    // public int yPosIfMoveUp(int delta) {
    //     return yPos += 1 * SPEED * delta;
    // }

    /** Find the current global tileID that the player is standing on.
     * -1 is returned if the player does not stand on any tile.
     * @param xPos the world xPosition of the target
     * @param yPos the world yPosition of the target
     */
    private int curTileID(int xPos, int yPos, TiledMap map) {
        if (xPos > 0 && yPos > 0) {
            int xT = xPos/map.getTileWidth();
            int yT = yPos/map.getTileHeight();
            return map.getTileId(xT, yT, 0);
        }
        return -1;
    }



}
















