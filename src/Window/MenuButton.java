package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import Tiles.Assets;

public class MenuButton extends Button {

	public MenuButton(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, Assets.menu, x, y, width, height);		
		mouseDown = Assets.menu;
		mouseOver = Assets.menuDown;
		setMouseDownImage(mouseDown);
	}
}
