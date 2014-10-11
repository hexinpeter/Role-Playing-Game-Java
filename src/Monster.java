public abstract class Monster extends Uncontrollable
{
	protected float speed;
	
	/** When the monster is attacked from fromX,fromY with hp of damage */
	public abstract void attacked(int hp);
}