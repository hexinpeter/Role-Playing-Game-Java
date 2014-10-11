import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Sword extends Item
{
	private int supDamage = 30;
	private final String imageLocation = "assets/items/sword.png";

	public Sword(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
	}

	public int getsupDamage() {
		return supDamage;
	}
}