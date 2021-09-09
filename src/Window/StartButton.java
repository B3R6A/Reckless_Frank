package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import Tiles.Assets;

public class StartButton extends Button {

	public StartButton(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, Assets.start, x, y, width, height);		
		mouseDown = Assets.start;
		mouseOver = Assets.startDown;
		setMouseDownImage(mouseDown);
	}
}
