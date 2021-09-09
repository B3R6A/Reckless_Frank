package Play;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.Level;
import Tiles.Tile;
import Tiles.World;

public class Tree extends Entity {
	public Image palm;
	private Random rand;
	public int treeGenerator;
	public Tree(float x, float y, float width, float height) {
		super(x, y, width, height);
		rand= new Random();
		int r=rand.nextInt(3) +1;
		try {
			palm = new Image("res/palme_0" + r + ".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(World.vect[(int)x/Tile.tilewidth]==1) this.y=y+Tile.tileheight;
		if(World.vect[(int)x/Tile.tilewidth]==2) this.y=y+Tile.tileheight;
	}


	@Override
	public void update() {
		if(hitbox.intersects(Level._99scimmie.get(0).hitbox)) {
			Player.ammo =15;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.translate(Level.worldCam.getCamX(),0);
		//g.draw(hitbox);
		g.translate(-Level.worldCam.getCamX(),0);
		g.drawImage(palm, shiftX, shiftY, shiftX + width, shiftY + height, 0, 0, 128, 192);
	}
	
}
