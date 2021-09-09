package Tiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Core.Game;
import States.Level;

public class Background {
	private float x, y;
	private Image sky_color;
	private Image farGround_cloud, foreGround_cloud;
	private Image farGround_hill, foreGround_hill;
	public Background() {
		this.x = 0;
		this.y = 0;
		try {
			sky_color = new Image("res/skyParts/sky_color.png");
			farGround_cloud=new Image("res/skyParts/mid_ground_cloud_1.png");
			foreGround_cloud =new Image("res/skyParts/mid_ground_cloud_2.png");
			farGround_hill=new Image("res/GUI_1.png");
			foreGround_hill=new Image("res/GUI_0.png");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update() {
		setX(x-0.3f);
		if(getX() * 2 <= -1280) setX(0);
	}
	
	public void render(Graphics g) {
		sky_color.draw(0, 0, Game.WIDTH, Game.HEIGHT, 0,0,14,1536);
		farGround_cloud.draw(getX(),getY()-150);
		farGround_cloud.draw(getX()+2048,getY()-150);
		foreGround_cloud.draw(getX(),getY()+200);		
		foreGround_cloud.draw(getX()+2048,getY()+200);
		g.translate(-Level.worldCam.getCamX()/2, 0);
		farGround_hill.draw(0, y-100);
		farGround_hill.draw(-farGround_hill.getWidth(), y-100);
		farGround_hill.draw(farGround_hill.getWidth(), y-100);
		g.translate(Level.worldCam.getCamX()/2, 0);
		
		g.translate(-Level.worldCam.getCamX(), 0);
		foreGround_hill.draw(0, y-150);
		foreGround_hill.draw(-foreGround_hill.getWidth(), y-150);
		foreGround_hill.draw(foreGround_hill.getWidth(), y-150);
		g.translate(Level.worldCam.getCamX(), 0);

	}
	public void renderMenu(Graphics g) {
		sky_color.draw(0, 0, Game.WIDTH, Game.HEIGHT, 0,0,14,1536);
		farGround_cloud.draw(-x,y-150);
		farGround_cloud.draw(-x-2048,y-150);
		foreGround_cloud.draw(-x,y+200);		
		foreGround_cloud.draw(-x-2048,y+200);
	
		farGround_hill.draw(getX(), y);
		farGround_hill.draw(getX() +farGround_hill.getWidth(), y);
			
		foreGround_hill.draw(2*getX(), y);
		foreGround_hill.draw(2*getX() + foreGround_hill.getWidth(), y);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

}
