import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Skeleton extends AggressiveMonster
{
	private final String imageLocation = "assets/units/skeleton.png";

	public Skeleton(double xPos, double yPos) throws 
	 SlickException{
	 	//System.out.println("created successfully");
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
		hitP = 100;
		maxHP = 100;
		maxDamage = 16;
		maxCooldown = 500;
		speed = 0.25f;
	}
}