import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Uncontrollable extends Unit
{
	private Color BG = Color.black;
	private Color HP = Color.red;

	public abstract void update(int delta, World w);

	public void render(Graphics g, int cameraMinX, int cameraMinY) {
		int screenX = (int)(xPos-cameraMinX);
		int screenY = (int)(yPos - cameraMinY);
		image.drawCentered(screenX, screenY);
  	
  	renderInfo(g, this, cameraMinX, cameraMinY);
	}

	/** Render the name and HP level above the head */
	public void  renderInfo(Graphics g, Unit unit, int cameraMinX, int cameraMinY) {
		int screenX = (int)(unit.getxPos()-cameraMinX) - 30;
		int screenY = (int)(unit.getyPos() - cameraMinY) - 45; // the yPos on screen to be rendered]
		int barHeight = 15;
		int barWidth = 70;
		float percentage = unit.gethitP() / (float)unit.getmaxHP();

		g.setColor(BG);
		g.fillRect(screenX, screenY, barWidth, barHeight);
		
		g.setColor(HP);
		g.fillRect(screenX, screenY, barWidth*percentage, barHeight);
		
		g.setColor(BG);
		g.drawString(unit.getClass().getSimpleName(), screenX, screenY);
	}
}