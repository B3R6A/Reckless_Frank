package Play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.Level;
import Tiles.Tile;
import Tiles.World;

public class Projectile extends Entity{
	public Enemy gorilla;
	public Image renderedImage;
	private boolean dir;
	public Image cocco_left;
	public Image cocco_right;
	private int DMG;
	public Projectile(float x, float y, float width, float height, boolean direction) {
		super(x, y, width, height);
		this.dir =direction;
		if(dir)vx=4f;
		else vx=-4f;
		vy=3f;
		try {
			cocco_left = new Image("res/cocco_left.png");
			cocco_right = new Image("res/cocco_right.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.DMG=150;
		
	}
	
	public int getDamage() {
		return DMG;
		
	}

	@Override
	public void update() {
		
		if(hitbox.intersects(World.list.get((int) (x/Tile.tilewidth))) ){
			vy=0;
			Player.isShooting= false;
		}else {
			vy-=0.2;
		}
		
		for(int i = 1;i<=Level.countGorilla;i++) {
			if(hitbox.intersects(Level._99scimmie.get(i).hitbox)) {
				vy=0;
				Player.isShooting=false;
			}
		}
		
		if(x <= 1 || x >= Level.world.getWorldWidth() -1) {
			vx = 0;
			vy = 0;
			Player.isShooting= false;
		}
		
		setX(x+vx);
		hitbox.setX(shiftX);
		setY(y-vy);
		hitbox.setY(shiftY);
		
		if(dir)renderedImage=cocco_right;
		else renderedImage=cocco_left;
	}

	@Override
	public void render(Graphics g) {
		g.translate(Level.worldCam.getCamX(),0);
			//g.draw(hitbox);
			g.translate(-Level.worldCam.getCamX(), 0);
			g.drawImage(renderedImage, shiftX, shiftY,shiftX+width, shiftY+height, 0, 0, 64, 64);
	}


}
