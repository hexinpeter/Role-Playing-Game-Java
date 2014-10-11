import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bat extends Monster
{
	private String mode; // "Escape" or "Wander"
	private int modeTimer; // in milliseconds
	private final String imageLocation = "assets/units/dreadbat.png";
	private int dirX;
	private int dirY;
	private final int ESCAPE_TIME = 5000;
	private final int WANDER_TIME = 3000;


	public Bat(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
		hitP = 40;
		maxHP = 40;
		maxDamage = 0;
		maxCooldown = 0;
		speed = 0.2f;
		mode = "Wander";
		modeTimer = 0;
		dirX = -1;
		dirY = 1;
	}

	public void update(int delta, World w) {
		if (hitP == 0) {
			exist = false;
		}

		modeTimer += delta;
		Random rand = new Random();
		if (mode == "Wander" && modeTimer > WANDER_TIME) {
			modeTimer = 0;
			dirX = rand.nextInt(3) - 1;
			dirY = rand.nextInt(3) - 1;
		}
		if (mode == "Escape" && modeTimer > ESCAPE_TIME) {
			modeTimer = 0;
			mode = "Wander";
		}
		
		if (mode == "Escape") {
			if (xPos == World.player.getxPos()) {
				dirX = 1;
			}
			if (xPos != World.player.getxPos()) {
				double distX = xPos - World.player.getxPos();
				dirX = (int)(distX / Math.abs(distX));
			}
			if (yPos == World.player.getyPos()) {
				dirY = 1;
			}
			if (yPos != World.player.getyPos()) {
				double distY = yPos - World.player.getyPos();
				dirY = (int)(distY / Math.abs(distY));
			}
		}
		
		move(dirX, dirY, delta, speed, w); 
	}

	/** When the monster is attacked from fromX,fromY with hp of damage */
	public void attacked(int hp) {
		hitP = ((hitP - hp) > 0) ? (hitP - hp) : 0;
		mode = "Escape";
	}
}