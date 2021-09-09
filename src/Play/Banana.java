package Play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.Level;
import Tiles.Tile;
import Tiles.World;

public class Banana extends Entity{
	public Image bananaImage;
	public boolean isDown = false;
	public int id;
	public Banana(float x, float y, float width, float height) {
		super(x, y, width, height);
		id=0;
		try {
			bananaImage = new Image("res/banana.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public int findHolder() { // get id
		for(int i = 0; i<=Level.countGorilla;i++) {
			if(Level._99scimmie.get(i).banana == true)
				return i;
		}
		return -1;
	}
	
	@Override
	public void update() {
		id = findHolder();
		if(id==-1)isDown =true;
		if(!isDown) {
			setX(Level._99scimmie.get(id).getX());
			setY(Level._99scimmie.get(id).shiftY);

		}else {
			if(hitbox.intersects(World.list.get((int) x/Tile.tilewidth))){
				vy=0;
			}else vy-=0.2f;
			
			setY(y-vy);
		}
		hitbox.setX(shiftX);
		hitbox.setY(shiftY);

	}

	public void render(Graphics g) {
		g.translate(Level.worldCam.getCamX(),0);
		//g.draw(hitbox);
		g.translate(-Level.worldCam.getCamX(),0);
		g.drawImage(bananaImage, shiftX, shiftY);
	}
	
}
