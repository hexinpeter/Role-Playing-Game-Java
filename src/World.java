/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Xin He <hex1@student.unimelb.edu.au>
 */

import java.util.ArrayList;

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

    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenWidth = RPG.screenWidth;
    /** Screen height, in pixels. */
    public final int screenHeight = RPG.screenHeight;

    private static TiledMap map;
    private static Player player;
    private static Camera camera;
    private static ArrayList<Villager> villagerList;
    //private static ArrayList<Monster> monsterList;
    //private static ArrayList<Item> itemList;

    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // TODO: Fill in
        map = new TiledMap(MAP_LOCATION, MAP_TILESET_LOCATION);
        player = new Player((double)756, (double)684);
        camera = new Camera(player, screenWidth, screenHeight);
        villagerList = new ArrayList<Villager>();
        //monsterList = new ArrayList<Monster>();
        //itemList = new ArrayList<Item>();
        loadContent();
    }

    /** Create villagers and monsters. */
    private void loadContent() throws 
	 SlickException {
        // add villagers
        villagerList.add(new Garth((double)756, (double)870));
        //villagerList.add(new Evlvira((double)738, (double)549));
        //villagerList.add(new Prince((double)467, (double)679));

        // add monsters

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
        player.update(dir_x, dir_y, delta, this);
        camera.update();


        // update Uncontrollables
        for (Villager v : villagerList) {
            v.update(delta);
        }
        // // remove dead monsters
        // for (Monster m : monsterList) {
        //     if (m.gethitP < 0) {
        //         monsterList.remove(m)
        //     }
        // }

        // for (Monster m : monsterList) {
        //     m.update(delta);
        // }
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
        player.render(g, camera.getMinX(), camera.getMinY());

        // render uncontrollables
        for (Villager v : villagerList) {
            v.render(g, camera.getMinX(), camera.getMinY());
        }
    }

    /** Calculate the starting X position where the portion of the map to be rendered.
     */
    private int calStartX() {
        return (camera.getMinX() > 0) ? (- camera.getMinX()%map.getTileWidth()) : Math.abs(camera.getMinX()); 
    }

    /** Calculate the starting Y position where the portion of the map to be rendered.
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


    /** Return True is blocking exits on the specified map position, False otherwise.
     * @param xPos the world xPosition of the target
     * @param yPos the world yPosition of the target
     */
    public boolean hasBlocking(int xPos, int yPos) {
        if (map.getTileProperty(curTileID(xPos, yPos), "block", "0") == "0")
            return false;
        return true;
    }

    /** Find the current global tileID that the player is standing on.
     * -1 is returned if the player does not stand on any tile.
     * @param xPos the world xPosition of the target
     * @param yPos the world yPosition of the target
     */
    private int curTileID(int xPos, int yPos) {
        if (xPos > 0 && yPos > 0) {
            int xT = xPos/map.getTileWidth();
            int yT = yPos/map.getTileHeight();
            return map.getTileId(xT, yT, 0);
        }
        return -1;
    }

}




















