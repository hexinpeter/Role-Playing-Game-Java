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

	/** Update the player's position.
   * @param xMovement the units of movement on x axis.
   * @param yMovement the units of movement on y axis.
   * @param delta Time passed since last frame (milliseconds).
   */
	public void move(int xMovement, int yMovement, int delta, float speed, World world) {
	    float dist = speed * delta;        
	    int xPosTest = (int) (xPos + xMovement * speed * delta);
	    int yPosTest = (int) (yPos + yMovement * speed * delta);
	    int xDiagonal = (int) (xPos + xMovement * Math.sqrt(Math.pow(dist, 2)/2));
	    int yDiagonal = (int) (yPos + yMovement * Math.sqrt(Math.pow(dist, 2)/2));

	    if (!world.hasBlocking(xPosTest, (int)yPos) && (world.hasBlocking((int)xPos, yPosTest) || yMovement == 0)){
	      xPos = xPosTest;
	    }
	    if (!world.hasBlocking((int)xPos, yPosTest) && (world.hasBlocking(xPosTest, (int)yPos) || xMovement == 0)){
	      yPos = yPosTest;
	    }
	    if (xMovement != 0 && yMovement != 0 && !world.hasBlocking(xDiagonal, (int)yPos) && !world.hasBlocking((int)xPos, yDiagonal)){
	        xPos = xDiagonal;
	        yPos = yDiagonal;
	    }
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
