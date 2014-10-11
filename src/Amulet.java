import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Amulet extends Item
{
	private int supHP = 80;
	private final String imageLocation = "assets/items/amulet.png";

	public Amulet(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
	}

	public int getsupHP() {
		return supHP;
	}

}