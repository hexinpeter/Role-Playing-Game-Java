import java.util.Random;


public abstract class AggressiveMonster extends Monster
{
	private int cooldownTimer = 0; // in milliseconds

	public void attacked(double fromX, double fromY, int hp){
		System.out.println("actacked by " + hp);
		hitP = ((hitP - hp) > 0) ? (hitP - hp) : 0;
	}

	public void update(int delta, World w) {
		if ((cooldownTimer - delta)> 0) 
      cooldownTimer -= delta;
    if ((cooldownTimer - delta)< 0) 
      cooldownTimer = 0;
    if (hitP == 0) 
    	exist = false;	
	}

	/** move towards the Player when the Player is within the range of 150 pixels */
	public void seePlayer(Player p, int delta, World world) {
		int xMovement = 0, yMovement = 0;
		if ((xPos - p.getxPos()) > 0)  {
			xMovement = -1;
		}
		if ((xPos - p.getxPos()) < 0)  {
			xMovement = 1;
		}
		if ((yPos - p.getyPos()) > 0)  {
			yMovement = -1;
		}
		if ((yPos - p.getyPos()) < 0)  {
			yMovement = 1;
		}
		//System.out.println("meet p " + xPos + " " + yPos + "  Player: " + p.getxPos() + "  " + p.getyPos() + "cool " + cooldownTimer);

		move(xMovement, yMovement, delta, speed, world);
	}

	/** attacks the Player when the Player is within the range of 50 pixels */
	public void meetPlayer(Player p) {
		Random rand = new Random();
		if (cooldownTimer == 0) {
			p.attacked(rand.nextInt(maxDamage + 1));
			cooldownTimer = maxCooldown;
		}
		
	}

}