import org.newdawn.slick.Graphics;

public abstract class Item extends Entity
{

	/** Render the item according to the camera's view positions 
   * @param cameraMinX the world position of the camera's view's left border 
   * @param cameraMinY the world position of the camera's view's top border
   */
  public void render(Graphics g, int cameraMinX, int cameraMinY) {
  	if (exist) {
  		image.drawCentered((int)(xPos-cameraMinX), (int)(yPos - cameraMinY));
  	}
  }

	public void isPickedUp(){
		exist = false;
	}

	public void putBack(){
		exist = true;
	}
}