import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Elvira extends Villager
{
	private final String imageLocation = "assets/units/necromancer.png";
	

	public Elvira(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		image = new Image(imageLocation);
	}

	/** Heal the Player's health when Elvira meets Player */
	public void meetPlayer(Player p) {
		if (p.gethitP() == p.getmaxHP()) {
			String dialogue = "Return to me if you ever need healing.";
			super.setisSaying(dialogue);
		}

		else {
			String dialogue = "You're looking much healthier now.";
			super.setisSaying(dialogue);
			p.meetElvira();
		}
	}


}