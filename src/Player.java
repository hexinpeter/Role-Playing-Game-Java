/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Xin He <hex1@student.unimelb.edu.au>
 */

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;


public class Player extends Unit
{
    private float SPEED = 0.25f;
    private final String imageLocation = "assets/units/player.png";
    public ArrayList<Item> itemList;
    private int cooldownTimer; // in milliseconds;

	/** Create a new player object.
	 * @param imageLocation The location of the player's image.
	 */
	public Player(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		image = new Image(imageLocation);
        itemList = new ArrayList<Item>();
        hitP = 100;
        maxHP = 100;
        maxCooldown = 600;
        maxDamage = 26;
	}

    /** Gets full health when meets Elvira */
    public void meetElvira() {
        hitP = maxHP;
    }


    /** Update the player's position.
     * @param xMovement the units of movement on x axis.
     * @param yMovement the units of movement on y axis.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int xMovement, int yMovement, int delta, World world) {        
        move(xMovement, yMovement, delta, SPEED, world);
        if ((cooldownTimer - delta)> 0) 
            cooldownTimer -= delta;
        if ((cooldownTimer - delta)< 0) 
            cooldownTimer = 0;
    }
    
    /** Add item to its itemList */
    public void addItem(Item i) {
        itemList.add(i);
        if (i instanceof Tome) {
            Tome t = (Tome) i;
            maxCooldown += t.getsupCooldown();
        }            
        if (i instanceof Sword) {
            Sword s = (Sword) i;
            maxDamage += s.getsupDamage();
        }
        	
        if (i instanceof Amulet) {
            Amulet a = (Amulet) i;
            maxHP += a.getsupHP();
        }
    }

    public void attacked(int hp) {
        hitP = (hitP - hp) > 0 ? (hitP - hp) : 0;
    }

    public void attack(Monster m) {
        Random rand = new Random();
        if (cooldownTimer == 0) {
            m.attacked(xPos, yPos, rand.nextInt(maxDamage + 1));
            cooldownTimer = maxCooldown;
        }
        System.out.println(cooldownTimer + " attack" );
    }

    /** Check if the Player has an item of class cls */
    public boolean hasItem(Class<?> cls) {
        for (Item i : itemList) {
            if (i.getClass() == cls) {
                return true;
            }
        }
        return false;
    }
    
    /** Render the player according to the camera's view positions 
     * @param cameraMinX the world position of the camera's view's left border 
     * @param cameraMinY the world position of the camera's view's top border
     */
    public void render(Graphics g, int cameraMinX, int cameraMinY) {
    	image.drawCentered((int)(xPos-cameraMinX), (int)(yPos - cameraMinY));
    }

    /** Update the player's position.
     * @param xMovement the units of movement on x axis.
     * @param yMovement the units of movement on y axis.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void move(int xMovement, int yMovement, int delta, float speed, World world) {        
      int xPosTest = (int) (xPos + xMovement * speed * delta);
      int yPosTest = (int) (yPos + yMovement * speed * delta);

      if (!world.hasBlocking(xPosTest, (int)yPos))
            xPos = xPosTest;
      if (!world.hasBlocking((int)xPos, yPosTest))
          yPos = yPosTest;
    }

}
















