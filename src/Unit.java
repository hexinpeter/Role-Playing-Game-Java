import org.newdawn.slick.tiled.TiledMap;


public abstract class Unit extends Entity
{
	protected int maxHP, maxCooldown, maxDamage, hitP;

	public int gethitP() {
		return hitP;
	}

	public int getmaxHP() {
		return maxHP;
	}

	public int getmaxCooldown() {
		return maxCooldown;
	}

	public int getmaxDamage() {
		return maxDamage;
	}

	/** Update the player's position.
   * @param xMovement the units of movement on x axis.
   * @param yMovement the units of movement on y axis.
   * @param delta Time passed since last frame (milliseconds).
   */
  public abstract void move(int xMovement, int yMovement, int delta, float speed, World world);
	
}