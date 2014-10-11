/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Xin He <hex1@student.unimelb.edu.au>
 */

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;



/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
    private final String MAP_LOCATION = "assets/map.tmx";
    private final String MAP_TILESET_LOCATION = "assets/"; 
    private final String PANEL_PNG_LOCATION = "assets/panel.png"; 

    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenWidth = RPG.screenWidth;
    /** Screen height, in pixels. */
    public final int screenHeight = RPG.screenHeight;

    private static TiledMap map;
    private static Player player;
    private static Camera camera;
    private static ArrayList<Villager> villagerList;
    private static ArrayList<Monster> monsterList;
    private static ArrayList<Item> itemList;
    ArrayList<Entity> allEntities;
    private static Image panel;

    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // TODO: Fill in
        map = new TiledMap(MAP_LOCATION, MAP_TILESET_LOCATION);
        player = new Player((double)756, (double)684);
        camera = new Camera(player, screenWidth, screenHeight);

        panel = new Image(PANEL_PNG_LOCATION);

        villagerList = new ArrayList<Villager>();
        monsterList = new ArrayList<Monster>();
        itemList = new ArrayList<Item>();
        allEntities = new ArrayList<Entity>();
        loadContent();
    }

    /** Create villagers and monsters. */
    private void loadContent() throws 
	 SlickException {
        // add villagers
        villagerList.add(new Garth((double)756, (double)870));
        villagerList.add(new Elvira((double)738, (double)549));
        villagerList.add(new Prince((double)467, (double)679));

        // add items
        itemList.add(new Sword((double)770, (double)500));
        itemList.add(new Amulet((double)791, (double)600));
        itemList.add(new Tome((double)770, (double)700));
        itemList.add(new Elixir((double)791, (double)800));
        // add monsters
        monsterList.add(new Zombie((double)681, (double)501));

        allEntities.addAll(villagerList);
        allEntities.addAll(itemList);
        allEntities.addAll(monsterList);
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @param action Boolean value to determine if Player takes action towards its surrounding entities
     */
    public void update(int dir_x, int dir_y, boolean action, int delta)
    throws SlickException
    {
        // TODO: Fill in
        player.update(dir_x, dir_y, delta, this);
        camera.update();
        
        if (action) {
        	takeAction();
        }

        // // remove dead monsters
        // for (Monster m : monsterList) {
        //     if (m.gethitP() < 0) {
        //         monsterList.remove(m);
        //     }
        // }
        
        // update Uncontrollables
        for (Villager v : villagerList) 
            v.update(delta, this);
        for (Monster m : monsterList) {
            m.update(delta, this);
        }

        // Aggressive Monster sees/meets Player
        ArrayList<Entity> seeRange = surroundingEntities(AggressiveMonster.class, (double)51, (double)150);
        ArrayList<Entity> meetRange = surroundingEntities(AggressiveMonster.class, (double)0, (double)50);        
        for (Entity m : seeRange) {
        	AggressiveMonster a = (AggressiveMonster) m;
            a.seePlayer(player, delta, this);
        }
        for (Entity m : meetRange) {
        	AggressiveMonster a = (AggressiveMonster) m;
            a.meetPlayer(player);
        }
        
    }

    /** Take action towards Player's surrounding entities */
    private void takeAction()
    {
        ArrayList<Entity> range50 = surroundingEntities(Entity.class, (double)0, (double)50);
        //System.out.println(range50);

        for (Entity e : range50) {
            // pick up item
            if (e instanceof Item) {
            	Item i = (Item) e;
                i.isPickedUp();
                player.addItem(i);
                //System.out.println("picked up" + e);
            }

            // talk to villager
            if (e instanceof Villager) {
                Villager v = (Villager) e;
                v.meetPlayer(player);
                //System.out.println("meet " + e + " " + villagerList.size());
            }

            // attack Monster
            if (e instanceof Monster) {
                Monster m = (Monster) e;
                player.attack(m);
            }
        }
    }


    /** Return the list of entities within the specified range of Player */
    private ArrayList<Entity> surroundingEntities(Class<?> cls, double startRange, double endRange) {
        ArrayList<Entity> nearEntityList = new ArrayList<Entity>();
        ArrayList<Entity> targetEntities = new ArrayList<Entity>();
        
        // filter the Entities
        for (int i = 0; i < allEntities.size() ; i++) {
            if (cls.isAssignableFrom(allEntities.get(i).getClass())) {
                targetEntities.add(allEntities.get(i));
            }
        }

        for (Entity v : targetEntities) {
            double dist = calDistance(player, v);
            if (dist<=endRange && dist>=startRange && v.getexist()) {
                nearEntityList.add(v);
            }
        }
        return nearEntityList;
    }


    /** Calculate the Manhattan distance between two entites */
    private double calDistance(Entity a, Entity b) {
        double disX = Math.abs(a.getxPos() - b.getxPos());
        double disY = Math.abs(a.getyPos() - b.getyPos());
        return disX + disY;
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

        for (Entity e : allEntities) {
            if (e.getexist())
                e.render(g, camera.getMinX(), camera.getMinY());
        }

        renderPanel(g);
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


    /** Return true is blocking exits on the specified map position, false otherwise.
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
    

    /** Render the panel. */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.screenHeight - RPG.PANELHEIGHT);
        
        // Display the player's health
        text_x = 15;
        text_y = screenHeight - RPG.PANELHEIGHT + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = player.gethitP() + "/" + player.getmaxHP();             // TODO: HP / Max-HP
        bar_x = 90;
        bar_y = RPG.screenHeight - RPG.PANELHEIGHT + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = player.gethitP()/(float)player.getmaxHP();     // TODO: HP / Max-HP
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = player.getmaxDamage() + "";                              // TODO: Damage
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = player.getmaxCooldown() + "";                                    // TODO: Cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.screenHeight - RPG.PANELHEIGHT + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.screenHeight - RPG.PANELHEIGHT
            + ((RPG.PANELHEIGHT - 72) / 2);
        for (Item i : player.itemList) {
            i.getimage().draw(inv_x, inv_y);
            inv_x += 72;           
        }        
    }
}




















