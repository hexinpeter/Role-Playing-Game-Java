import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Draelic extends AggressiveMonster
{
	private final String imageLocation = "assets/units/necromancer.png";

	public Draelic(double xPos, double yPos) throws 
	 SlickException{
	 	System.out.println("created successfully");
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
		hitP = 140;
		maxHP = 140;
		maxDamage = 30;
		maxCooldown = 400;
		speed = 0.25f;
	}
}