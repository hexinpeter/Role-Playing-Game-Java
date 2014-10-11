import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tome extends Item
{
	private int supCooldown = -300;
	private final String imageLocation = "assets/items/book.png";

	public Tome(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
	}

	public int getsupCooldown() {
		return supCooldown;
	}
}