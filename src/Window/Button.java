package Window;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import Core.Game;

public class Button extends MouseOverArea {

	protected Image mouseDown, mouseOver, mousePressed;
	public Button(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, image, x, y, width, height);
	}	
	public boolean update() {
		if(this.isMouseOver()) {
			setMouseOverImage(mouseOver);
			if(Game.getMouseManager().left) return true;
		}else setMouseDownImage(mouseDown);
		return false;
	};
}