package Play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

public abstract class Entity{
	protected float x, y;
	protected float width, height;
	protected float shiftX, shiftY;
	protected float vx =0 , vy =0;
	
	protected Rectangle hitbox;
	
	
	public Entity (float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		shiftX = x -  width/2;
		shiftY = y - height;
		hitbox = new Rectangle(shiftX,shiftY,width,height);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	public void setX(float newX) {
		x = newX;
		shiftX = newX - (width/2);
	}
	public void setY(float newY) {
		y = newY;
		shiftY = newY - (height);
	}
	public abstract void update();
	public abstract void render(Graphics g);
}
