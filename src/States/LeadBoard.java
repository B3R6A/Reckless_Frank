package States;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import Core.Game;
import Tiles.Assets;
import Tiles.Background;
import Window.ResetButton;

public class LeadBoard extends BasicGameState{
	private int id;
	private ResetButton back;
	private GUIContext GUI;
	private Background background;
	private List <String> records = new LinkedList<String>();
	public static TrueTypeFont font;
	
	public LeadBoard (int stateId) {
		this.id = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Assets.initLeaves();
		back = new ResetButton (gc, Assets.reset, 25, 25 ,150, 150);
		background = new Background();
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/edosz.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(40f); // set font size
			font= new TrueTypeFont(awtFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}   
		records.clear();
		setList();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.renderMenu(g);
		g.drawImage(Assets.darken, 0, 0);
		back.render(GUI, g);
		
		g.drawImage(Assets.board, Game.WIDTH/2 -186, 160, Game.WIDTH/2 +186, 660, 0,0,964,1295 );
		g.drawImage(Assets.highscores, Game.WIDTH/2 - Assets.highscores.getWidth()/2, 70);

		for(int i = 0; i<records.size();i++) {
			font.drawString(Game.WIDTH/2 - font.getWidth(records.get(i))/2, 250 + i*35, records.get(i));
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		background.update();
		if(back.update())gc.reinit();
	}
	
	public void setList() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try {
			readFile = new FileReader("highscore.txt");
			reader = new BufferedReader(readFile);
			String tmp = reader.readLine();
			for(int  i =0; tmp != null; i++) {
				records.add(i, tmp);
				tmp =reader.readLine();
			}
		} catch ( IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public int getID() {
		return id;
	}

}
