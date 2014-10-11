import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Prince extends Villager
{
	private final String imageLocation = "assets/units/prince.png";
	

	public Prince(double xPos, double yPos) throws 
	 SlickException{
		this.xPos = xPos;
		this.yPos = yPos;
		hitP = 1;
		maxHP = 1;
		maxDamage = 0;
		maxCooldown = 0;
		image = new Image(imageLocation);
	}

	/** Heal the Player's health when Elvira meets Player */
	public void meetPlayer(Player p) {
		if (!p.hasItem(Elixir.class)) {
			String dialogue = "Please seek out the Elixir of Life to cure the king.";
			super.setisSaying(dialogue);
		}

		else {
			String dialogue = "The elixir! My father is cured! Thankyou!";
			super.setisSaying(dialogue);
		}
	}
}