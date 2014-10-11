import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Garth extends Villager
{
	private final String imageLocation = "assets/units/peasant.png";
	

	public Garth(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		image = new Image(imageLocation);
	}

	public void meetPlayer(Player p) {

	}
}