import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public abstract class Villager extends Uncontrollable
{
	private String isSaying;
	private int sayTimer = 0; // in milliseconds
	private final int DISPLAY_DURATION = 4000; // in milliseconds
	private final Color TEXT_COLOR = Color.black; 

	/** Manage the duration of Villager is message display.
   * @param delta Time passed since last frame (milliseconds).
   */ 
	public void update(int delta){
		if (sayTimer + delta > DISPLAY_DURATION){
			sayTimer = 0;
			isSaying = null;
		}
		sayTimer += delta;
	}

	/** Render the Villager and its message according to the camera's view positions 
	 * @param g The Slick graphics object, used for drawing.
   * @param cameraMinX the world position of the camera's view's left border 
   * @param cameraMinY the world position of the camera's view's top border
   */
  public void render(Graphics g, int cameraMinX, int cameraMinY) {
  	int screenX = (int)(xPos-cameraMinX);
  	int screenY = (int)(yPos - cameraMinY);
  	image.drawCentered(screenX, screenY);

  	if (isSaying != null) {
  		g.setColor(TEXT_COLOR);
  		g.drawString(isSaying, screenX, screenY+32);
  	}
  }

  public void setisSaying(String message) {
  	isSaying = message;
  }

  public abstract void meetPlayer(Player p);
}