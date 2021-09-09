package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.Game;
import Tiles.Assets;
import Tiles.Background;
import Window.*;

public class Menu extends BasicGameState{
	private int id;
	private Image title;
	protected Input mouseInput;
	private GUIContext GUI;
	private StartButton start;
	private LeadButton leadboard;
	private ExitButton exit;
	private Background background;
	public Menu (int stateId) {
		this.id = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Assets.initButtons();;
		mouseInput= gc.getInput();
		mouseInput.addMouseListener((MouseListener) Game.getMouseManager());
		start = new StartButton(gc, Assets.start, (Game.WIDTH/2)-75, (Game.HEIGHT/2), 150, 150);
		leadboard = new LeadButton(gc, Assets.leadboard,(Game.WIDTH/2)-275, (Game.HEIGHT/2), 150, 150);
		exit = new ExitButton(gc, Assets.exit,(Game.WIDTH/2)+125, (Game.HEIGHT/2), 150, 150);
		background = new Background();
		title = new Image("res/title.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.renderMenu(g);
		g.drawImage(title, Game.WIDTH/2 - title.getWidth()/2, 30);
		start.render(GUI, g);
		leadboard.render(GUI, g);
		exit.render(GUI, g);
		g.drawString("OOP Project - Bergamini S. Zanni E. - A.A. 2020/2021", Game.WIDTH/2 - 235, Game.HEIGHT - 35);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Game.getMouseManager().update();
		background.update();
		if(start.update()) {
			sbg.addState(new Level(Game.level));
			sbg.getState(Game.level).init(gc, sbg);
			leave(gc, sbg);
			sbg.enterState(Game.level);
		}
		if(leadboard.update()) {
			sbg.addState(new LeadBoard(Game.leadboard));
			sbg.getState(Game.leadboard).init(gc, sbg);
			leave(gc, sbg);

			sbg.enterState(Game.leadboard);
		}
		if(exit.update()) {
			System.exit(0);
		}

	}

	@Override
	public int getID() {
		return id;
	}

}