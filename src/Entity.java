import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;



public abstract class Entity
{
	protected double xPos;
	protected double yPos;
	protected Image image;

	public double getxPos(){
		return xPos;
	}

	public double getyPos(){
		return yPos;
	}

	public abstract void render(Graphics g, int xPos, int yPos);
}