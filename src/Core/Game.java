package Core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import States.*;


public class Game extends StateBasedGame{
	public static final String name = "Reckless_Frank";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int menu = 0;
	public static final int level = 1;
	public static final int leadboard = 2;
	private static MouseManager Mouse;
	private static KeyManager Keyboard;

	public Game(String name) {
		super(name);
		this.addState(new Menu(menu));
		Keyboard = new KeyManager();
		Mouse = new MouseManager();
		

	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);

		this.enterState(menu);
		//System.exit(0);
	}

	public static KeyManager getKeyManager() {
		return Keyboard;
	}
	public static MouseManager getMouseManager() {
		return Mouse;
	}

	public static void main(String[] args) {
		AppGameContainer appgc; // basically is the window itself
		try {
			appgc = new AppGameContainer(new Game(name));
			appgc.setTargetFrameRate(60);
			appgc.setShowFPS(false);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}

	}
}
