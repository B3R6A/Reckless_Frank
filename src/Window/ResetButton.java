package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import Tiles.Assets;

public class ResetButton extends Button {

	public ResetButton(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, Assets.reset, x, y, width, height);		
		mouseDown = Assets.reset;
		mouseOver = Assets.resetDown;
		setMouseDownImage(mouseDown);
	}
}
