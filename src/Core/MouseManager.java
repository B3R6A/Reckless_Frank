package Core;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MouseManager implements MouseListener {
	public boolean[] buttons;
	public boolean left, middle, right;
	public int wheel;
	public MouseManager() {
		buttons = new boolean[3];
		this.wheel = 50;
	}
	
	public void update() {
		left=buttons[Input.MOUSE_LEFT_BUTTON];
		right=buttons[Input.MOUSE_RIGHT_BUTTON];
		middle=buttons[Input.MOUSE_MIDDLE_BUTTON];
	}
	
	public void mouseClicked(int button, int x, int y, int count) {
		buttons[button] = true;
	}

	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(int button, int x, int y) {
		buttons[button] = true;
	}

	public void mouseReleased(int button, int x, int y) {
		buttons[button] = false;
	}

	public void mouseWheelMoved(int change) {
		if(change<0 && wheel<=100) this.wheel+= change;
		if(change>0 && wheel>=0) this.wheel+= change;
	}
	
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}
	public void inputStarted() {
		// TODO Auto-generated method stub

	}
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}
}
