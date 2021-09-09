package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import Tiles.Assets;

public class LeadButton extends Button {

	public LeadButton(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, Assets.leadboard, x, y, width, height);		
		mouseDown = Assets.leadboard;
		mouseOver = Assets.leadboardDown;
		setMouseDownImage(mouseDown);
	}
}
