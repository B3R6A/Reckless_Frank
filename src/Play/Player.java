package Play;
import org.newdawn.slick.*;

import Core.Game;
import States.Level;
import Tiles.Tile;
import Tiles.World;
public class Player extends Monke {
	protected SpriteSheet jumping_left, jumping_right;
	protected Animation jumping_l, jumping_r, idle_l, idle_r;
	
	protected boolean isJumping = false;
	private boolean stillPressing = false;
	public static boolean isShooting= false;
	
	public Camera cam;
	public Projectile weapon;
	public static int ammo;
	
	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		banana = true;
		try {
			running_left = new SpriteSheet("res/SPRITESHEETRUNMONKE_1_LEFT.png", 160, 200);
			running_right = new SpriteSheet("res/SPRITESHEETRUNMONKE_1_RIGHT.png", 160, 200);

			jumping_left = new SpriteSheet("res/SPRITESHEETJUMPMONKE_1_LEFT.png", 160, 200);
			jumping_right = new SpriteSheet("res/SPRITESHEETJUMPMONKE_1_RIGHT.png", 160, 200);

			current = new Animation(jumping_right,0,0,4,0,true,80, true);

			idle_l = new Animation(jumping_left,0,0,4,0,true,80, true);
			idle_r = new Animation(jumping_right,0,0,4,0,true,80, true);


			running_l = new Animation(running_left,0,0,7,3,true,20, true);
			running_r = new Animation(running_right,0,0,7,3,true,20, true);
			jumping_l = new Animation(jumping_left,3,0,7,3,true, 60, true);
			jumping_r = new Animation(jumping_right,3,0,7,3,true,60, true);
			idle_l.setPingPong(true);
			idle_r.setPingPong(true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		cam = Level.worldCam;
		hitbox.setX(shiftX+(width/4));
		hitbox.setY(shiftY+(height/2));
		hitbox.setWidth(width/2);
		hitbox.setHeight(height/2);

		ammo=15;
	}

	@Override
	public void update() {
		
		if(Game.getKeyManager().left) {
			vx=-3f;
			direction = false;
			isRunning = true;
		}else if(Game.getKeyManager().right) {
			vx=3f;
			direction = true;
			isRunning = true;
		}else {
			vx =0;
			isRunning = false;
		}

		if(hitbox.intersects(World.list.get((int) (x/Tile.tilewidth))) && vy<4){
			isJumping = false;
			vy=0;
			if(y>Level.world.findY((int)x)) {
				setY(Level.world.findY((int)x));
				hitbox.setY(shiftY+(height/2));
			}
			if(isRunning && !isJumping) {
				if(World.vect[(int) (x/64)] ==1) {
					if(!direction) vy=-vx;
				}else if(World.vect[(int) (x/64)] ==2) {
					if(direction) vy=vx;
				}
			}
		}else {
			vy-=0.2;
		}

		if(Game.getKeyManager().up && !stillPressing && !isJumping) {
			vy=9;
			stillPressing = true;
			isJumping=true;
			jumping_l.restart();
			jumping_r.restart();
		}
		if(!Game.getKeyManager().up) stillPressing = false;


		setX(x+vx);
		hitbox.setX(shiftX+(width/4));
		setY(y-vy);
		hitbox.setY(shiftY+(height/2));
	
		cam.setCamX((int)x);
		cam.setCamY((int)y);
		cam.update();
	

		if(isRunning && !direction)current = running_l;
		if(isRunning && direction)current = running_r;
		if(isJumping && !direction)current = jumping_l;
		if(isJumping && direction)current = jumping_r;
		if(!isJumping && !isRunning) {
			if(!direction)current = idle_l;
			else if(direction)current = idle_r;
		}
		if(!banana && Level.banana.isDown && hitbox.intersects(Level.banana.hitbox)){
			banana = true;
			Level.banana.isDown = false;
		}

		if(Game.getKeyManager().space && !isShooting) {
			if(ammo>0) {
				isShooting = true;
				weapon = new Projectile(this.x, this.y-(this.height/2), 30, 30, direction);
				ammo--;
				 if(!isShooting) weapon = null;
			}
		}
	}
	
	public Projectile getProjectile() {
		return weapon;
	}
	@Override
	public void render(Graphics g, boolean isPaused) {
		//g.drawString("b: "+ banana, shiftX, shiftY- 15);
		//g.draw(hitbox);
		g.translate(Level.worldCam.getCamX(), 0);
		
		if(isPaused)current.stop();
		if(current.isStopped() && !isPaused) current.start();
		
		if (cam.getCamX() == cam.offsetMinX) current.draw(shiftX, shiftY , width, height);
		else if(cam.getCamX() == cam.offsetMaxX) current.draw(shiftX +(Game.WIDTH - Level.world.getWorldWidth()), shiftY , width, height);
		else current.draw((Game.WIDTH/2) - (width/2), shiftY , width, height);
		g.translate(-Level.worldCam.getCamX(), 0);
	}
	public int getAmmo() {
		return ammo;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	
}
