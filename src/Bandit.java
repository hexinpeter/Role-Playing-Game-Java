import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bandit extends AggressiveMonster
{
	private final String imageLocation = "assets/units/bandit.png";

	public Bandit(double xPos, double yPos) throws 
	 SlickException{
	 	System.out.println("created successfully");
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
		hitP = 40;
		maxHP = 40;
		maxDamage = 8;
		maxCooldown = 200;
		speed = 0.25f;
	}
}