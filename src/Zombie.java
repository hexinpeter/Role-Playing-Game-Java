import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombie extends AggressiveMonster
{
	private final String imageLocation = "assets/items/zombie.png";

	public Zombie(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
		hitP = 60;
		maxHP = 60;
		maxDamage = 10;
		maxCooldown = 800;
		speed = 0.25f;
	}
}