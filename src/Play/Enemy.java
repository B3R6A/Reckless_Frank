package Play;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import States.Level;

public class Enemy extends Monke{
	protected SpriteSheet hurt_left, hurt_right;
	protected SpriteSheet die_left, die_right;
	protected Animation hurt_l, hurt_r, die_l, die_r;
	protected float health;
	public float startHealth;

	protected boolean isDead = false, isHurt = false;

	public Enemy(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.startHealth = (200 * Level.difficulty) + 100;
		this.health = startHealth;
		// System.out.println(health);
		isRunning = true;
		hitbox.setWidth(width-40);
		try {
			running_left = new SpriteSheet("res/SPRITESHEETWALKGORILLA_1_LEFT.png", 240, 200);
			running_right = new SpriteSheet("res/SPRITESHEETWALKGORILLA_1_RIGHT.png", 240, 200);

			hurt_left = new SpriteSheet("res/SPRITESHEETHURTGORILLA_1_LEFT.png", 240, 200);
			hurt_right = new SpriteSheet("res/SPRITESHEETHURTGORILLA_1_RIGHT.png", 240, 200);

			die_left = new SpriteSheet("res/SPRITESHEETDIEGORILLA_1_LEFT.png", 240, 200);
			die_right = new SpriteSheet("res/SPRITESHEETDIEGORILLA_1_RIGHT.png", 240, 200);


			running_l =new Animation(running_left,0,0,4,3,true, 30, true);
			running_r =new Animation(running_right,0,0,4,3,true, 30, true);

			hurt_l =new Animation(hurt_left ,0,0,4,3,true, 30, true);
			hurt_r =new Animation(hurt_right,0,0,4,3,true, 30, true);

			die_l =new Animation(die_left,0,0,4,3,true, 30, true);
			die_r =new Animation(die_right,0,0,4,3,true, 30, true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(!banana) {
			if(Level.banana.getX()<=x) {
				vx=-1f;
				direction = false;
			}else {
				vx=1f;
				direction = true;
			}
		}

		if(banana) {
			if(x<=(Level.world.getWorldWidth()/2)) {
				vx=-1f;
				direction = false;
			}else {
				vx=1f;
				direction =true;
			}
			
			if(hitbox.intersects(Level.spawn_1.hitbox) || hitbox.intersects(Level.spawn_2.hitbox)){
				vx=0;
			}
		}	
		
		if(x == Level.banana.getX() && !banana)vx=0;

		if(isHurt || isDead) {
			isRunning=false;
			vx=0;
		}


		setX(x+vx);
		hitbox.setX(shiftX+20);
		setY(Level.world.findY((int) x));
		hitbox.setY(shiftY);

		if(hitbox.intersects(Level._99scimmie.get(0).hitbox) && Level._99scimmie.get(0).banana && !isDead) {
			Level._99scimmie.get(0).banana= false;
			banana = true;
		}

		if(!banana && Level.banana.isDown && hitbox.intersects(Level.banana.hitbox) && !isDead){
			banana = true;
			Level.banana.isDown = false;
		}

		if(Player.isShooting) {
			if(hitbox.intersects( ((Player)Level._99scimmie.get(0)).getProjectile().hitbox )){
				if(!isHurt)	damage(((Player)Level._99scimmie.get(0)).getProjectile().getDamage());
			}
		}

		if(current == die_l || current == die_r || current == hurt_l || current == hurt_r) {
			current.stopAt(19);
		}

		if(die_l.isStopped() || die_r.isStopped()) {
			Level.countGorilla--;
			Level._99scimmie.remove(this);
		}

		if(hurt_l.isStopped() || hurt_r.isStopped()) {
			if(isHurt) {
				//printStates();
				isHurt=false;
				if(health<=0) kill();
				else isRunning=true;
			}
		}
		
		if(isRunning && direction)current = running_r;
		if(isRunning && !direction)current = running_l;
		if(isHurt && direction)current = hurt_r;
		if(isHurt && !direction)current = hurt_l;
		if(isDead && direction) current= die_r;
		if(isDead && !direction) current= die_l;
	}

	public void kill() {
		isDead = true;
		Level.score +=100;
		if(banana) {
			dropBanana();
		}
		die_l.restart();
		die_r.restart();
	}


	public void damage(int DMG) {
		isHurt = true;
		health -= DMG;
		if(health<0)health=0;
		//System.out.println(health);
		hurt_l.restart();
		hurt_r.restart();

	}
	public void dropBanana() {
		banana = false;
		Level.banana.isDown =true; 
	}
	@Override
	public void render(Graphics g, boolean isPaused) {
		/*
		g.translate(Level.worldCam.getCamX(),0);
		g.draw(hitbox);
		g.translate(-Level.worldCam.getCamX(),0);
		*/
		//g.drawString("b: "+ banana + "\n#tile:" + tile + "\nTpos: " + ((int)x/64), shiftX, shiftY - 45);
		
		if(isPaused)current.stop();
		if(current.isStopped() && !isPaused) current.start();
		
		current.draw(shiftX, shiftY , width, height);
		g.setColor(Color.green);
		if(health/startHealth <= 0.5f)g.setColor(Color.yellow);
		if(health/startHealth <= 0.25f)g.setColor(Color.red);
		g.fillRect(x-(40*(health/startHealth)), shiftY-10, 80*(health/startHealth), 5);
	}
	public void printStates() {
		System.out.println(isRunning);
		System.out.println(isHurt);
		System.out.println(isDead);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
