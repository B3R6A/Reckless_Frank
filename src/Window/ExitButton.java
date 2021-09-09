package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import Tiles.Assets;

public class ExitButton extends Button {

	public ExitButton(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, Assets.exit, x, y, width, height);		
		mouseDown = Assets.exit;
		mouseOver = Assets.exitDown;

		setMouseDownImage(mouseDown);
	}
}

