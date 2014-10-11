import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Elixir extends Item
{
	private boolean isReturned;
	private final String imageLocation = "assets/items/elixir.png";

	public Elixir(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image(imageLocation);
	}

	public boolean getisReturned() {
		return isReturned;
	}

	public void isReturned() {
		isReturned = true;
	}
}