package Play;
import org.newdawn.slick.*;

public abstract class Monke extends Entity{	
	public boolean banana = false;
	protected SpriteSheet running_left, running_right;
	
	protected Animation current, running_l, running_r;
	
	protected boolean isRunning = false;
	protected boolean direction = true; // true = right
	
	public Monke(float x, float y, float width, float height) {
		super(x, y, width, height);	
	}
	public abstract void render(Graphics g, boolean b);
}
