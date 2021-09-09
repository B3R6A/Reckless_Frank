package Tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Assets {

	public static Image piano, discesa, salita, mid_d, mid_s, terra, cielo;
	public static Image start, reset, menu, leadboard, exit, startDown, resetDown, menuDown, leadboardDown, exitDown, darken, board;
	public static Image  blank, lose, pause, highscores;
	public static void initLeaves() {
		SpriteSheet leafSet;
		try {
		leafSet= new SpriteSheet("res/foglie.png", 501, 198);
		blank= leafSet.getSprite(0,0);
		lose= leafSet.getSprite(1,0);
		pause= leafSet.getSprite(0,1);
		highscores = leafSet.getSprite(1,1);
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void initButtons() {
		SpriteSheet buttonSet;
		try {
			darken = new Image("res/darken.png");
			board =new Image("res/bg.png");
			buttonSet = new SpriteSheet("res/assetsButton.png", 150, 150);
			start = buttonSet.getSprite(0, 0);
			reset = buttonSet.getSprite(1, 0);
			menu = buttonSet.getSprite(2, 0);
			leadboard = buttonSet.getSprite(3, 0);
			exit = buttonSet.getSprite(4, 0);
			
			startDown = buttonSet.getSprite(0, 1);
			resetDown = buttonSet.getSprite(1, 1);
			menuDown = buttonSet.getSprite(2, 1);
			leadboardDown = buttonSet.getSprite(3, 1);
			exitDown = buttonSet.getSprite(4, 1);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void init() {
		SpriteSheet tiles;
		try {
			tiles = new SpriteSheet("res/asset.png", 64, 64);
			piano = tiles.getSprite(0, 0);
			discesa = tiles.getSprite(1, 0);
			salita = tiles.getSprite(2, 0);
			mid_d = tiles.getSprite(3, 0);
			mid_s = tiles.getSprite(4, 0);
			terra = tiles.getSprite(5, 0);
			cielo = tiles.getSprite(6, 0);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
