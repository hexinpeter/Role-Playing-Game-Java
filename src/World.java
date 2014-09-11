/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Image;


/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
    private final String MAP_LOCATION = "assets/map.tmx";
    private final String MAP_TILESET_LOCATION = "assets/"; 
    private final String PLAYER_PNG_LOCATION = "assets/units/player.png";

    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenWidth = RPG.screenWidth;
    /** Screen height, in pixels. */
    public final int screenHeight = RPG.screenHeight;

    private static TiledMap map;
    private static Player player;
    private static Camera camera;

    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // TODO: Fill in
        map = new TiledMap(MAP_LOCATION, MAP_TILESET_LOCATION);
        player = new Player(PLAYER_PNG_LOCATION);
        camera = new Camera(player, screenWidth, screenHeight);

    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int dir_x, int dir_y, int delta)
    throws SlickException
    {
        // TODO: Fill in
        System.out.println("original: " + player.getxPos() + " " + player.getyPos());

        player.update(dir_x, dir_y, delta, map);

        // System.out.println("before update: " + player.getxPos() + " " + player.getyPos());

        // int playerTileID = curTileID(player);
        // if (map.getTileProperty(playerTileID, "block", "0") != "0"){
        // 	System.out.println("success");
        //     player.cancelUpdate();
        //     System.out.println("after update: " + player.getxPos() + " " + player.getyPos());

        // }

        // System.out.println("tile: " + map.getTileProperty(playerTileID, "block", "0"));
        
        camera.update();
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        // TODO: Fill in
        int numTileWidth = screenWidth/map.getTileWidth() + 2;
        int numTileHeight = screenHeight/map.getTileHeight() + 2;

        
        map.render(calStartX(), calStartY(), calStartTX(), calStartTY(), numTileWidth, numTileHeight);
        player.image.draw(playerX(player), playerY(player));

        g.drawString("camera postions: " + camera.getMinX() + "  " + camera.getMinY(), 100, 100);
        g.drawString("player pos: " + player.getxPos() + " " + player.getyPos(), 100, 250);
        g.drawString("" + calStartX() + " " + calStartY() + " " + calStartTX() + " " + calStartTY() + " " + numTileWidth + " " + numTileHeight, 100, 150);
        
    }

    /** Calculate the starting X postion where the portion of the map to be rendered.
     */
    private int calStartX() {
        return (camera.getMinX() > 0) ? (- camera.getMinX()%map.getTileWidth()) : Math.abs(camera.getMinX()); 
    }

    /** Calculate the starting Y postion where the portion of the map to be rendered.
     */
    private int calStartY() {
        return (camera.getMinY() > 0) ? (- camera.getMinY()%map.getTileHeight()) : Math.abs(camera.getMinY()); 
    }

    /** Calculate the x tile location to start rendering 
     */
    private int calStartTX() {
        return (camera.getMinX() < 0) ? 0 : camera.getMinX()/map.getTileWidth();
    }

    /** Calculate the x tile location to start rendering 
     */
    private int calStartTY() {
        return (camera.getMinY() < 0) ? 0 : camera.getMinY()/map.getTileHeight();
    }

    /** Calculate the player's x coordinate on the screen.
     * @param player the target player 
     */
    private int playerX(Player player) {
        return player.getxPos() - camera.getMinX() + calStartX();
        //return pW - cW + screenWidth/2;
    }

    /** Calculate the player's y coordinate on the screen
     * @param player the target player 
     */
    private int playerY(Player player) {
        return player.getyPos() - camera.getMinY() + calStartY();
    }

    // /** Find the current global tileID that the player is standing on.
    //  * -1 is returned if the player does not stand on any tile.
    //  * @param player the target player we are looking at.
    //  */
    // private int curTileID(Player player) {
    //     if (player.getxPos() > 0 && player.getyPos() > 0) {
    //         int xT = player.getxPos()/map.getTileWidth();
    //         int yT = player.getyPos()/map.getTileHeight();
    //         return map.getTileId(xT, yT, 0);
    //     }
    //     return -1;
    // }


    /** Determine the legitimate movement by the player, and update player.
     * @param player the target player
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    // private void playerUpdate(Player player, int dir_x, int dir_y, int delta) {

    // }
}




















