package Core;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class KeyManager implements KeyListener{

	public boolean[] keys;
	public boolean up, left, right, space, esc;


	public KeyManager() {
		keys = new boolean[256];
	}

	public void update() {
		up=keys[Input.KEY_UP];
		left=keys[Input.KEY_LEFT];
		right=keys[Input.KEY_RIGHT];
		space =keys[Input.KEY_SPACE];
		esc = keys[Input.KEY_ESCAPE];
	}

	@Override
	public void keyPressed(int key, char c) {
		keys[key] = true;
	}

	@Override
	public void keyReleased(int key, char c) {
		keys[key]=false;		
	}



	public void inputEnded() {
	}
	public void inputStarted() {
	}
	public boolean isAcceptingInput() {
		return true;
	}
	public void setInput(Input arg0) {
	}
}
