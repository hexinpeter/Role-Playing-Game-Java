import org.newdawn.slick.Graphics;

public abstract class Item extends Entity
{
	private boolean isPickedUp = false;

	public boolean getisPickedUp(){
		return isPickedUp;
	}

	/** Render the item according to the camera's view positions 
   * @param cameraMinX the world position of the camera's view's left border 
   * @param cameraMinY the world position of the camera's view's top border
   */
  public void render(Graphics g, int cameraMinX, int cameraMinY) {
  	if (!isPickedUp) {
  		image.drawCentered((int)(xPos-cameraMinX), (int)(yPos - cameraMinY));
  	}
  }

	public void isPickedUp(){
		isPickedUp = true;
		exist = false;
	}
}