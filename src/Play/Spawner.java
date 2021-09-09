package Play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.Level;
import Tiles.World;

public class Spawner extends Entity {
	public boolean hasGenerated =false;
	private Image bush;

	public Spawner(float x, float y, float width, float height) {
		super(x, y, width, height);
		if(World.vect[0]==1 && x==1)setY(y+64);
		if(World.vect[34]==2 && x>100)setY(y+64);

		try {
			bush = new Image("res/bush.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void render(Graphics g) {
		//g.drawRect(shiftX,shiftY,width,height);
		g.drawImage(bush, shiftX, shiftY);
	}

	public void update() {
		if(!Level.banana.isDown) {
			if(hitbox.intersects(Level._99scimmie.get(Level.banana.findHolder()).hitbox)) Level.gameOver = true;
		}
	}

	public void spawnEnemy() {
		Level._99scimmie.add(new Enemy(x, y-64, 90, 75));
		Level.countGorilla++;
		hasGenerated = true;
	}


}
