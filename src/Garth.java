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

	/** Set the dialogue when Garth meets the Player */
	public void meetPlayer(Player p) {
		System.out.println("Garth meets P");
		if (!p.hasItem(Amulet.class)) {
			String dialogue = "Find the Amulet of Vitality, across the river to the west.";
			super.setisSaying(dialogue);
		}

		else if (!p.hasItem(Sword.class)) {
			String dialogue = "Find the Sword of Strength - cross the river and back, on the east side.";
			super.setisSaying(dialogue);
		}

		else if (!p.hasItem(Tome.class)) {
			String dialogue = "Find the Tome of Agility, in the Land of Shadows.";
			super.setisSaying(dialogue);
		}

		else {
			String dialogue = "You have found all the treasure I know of.";
			super.setisSaying(dialogue);
		}
	}
}