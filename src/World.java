/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Xin He <hex1@student.unimelb.edu.au>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.SlickException;

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
        player.update(dir_x, dir_y, delta, map);
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
        player.image.drawCentered(player.getxPos()-camera.getMinX(), player.getyPos() - camera.getMinY());
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

}




















