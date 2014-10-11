import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;



public abstract class Entity
{
	protected double xPos;
	protected double yPos;
	protected Image image;
	protected boolean exist = true;

	public boolean getexist(){
		return exist;
	}

	public double getxPos(){
		return xPos;
	}

	public double getyPos(){
		return yPos;
	}

	public Image getimage(){
		return image;
	}

	public abstract void render(Graphics g, int xPos, int yPos);
}