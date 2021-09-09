package States;
import Play.*;
import Tiles.*;
import Core.*;
import Core.Timer;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import Window.*;

public class Level extends BasicGameState {
	private GUIContext GUI;
	private List <String> records = new LinkedList <String>();
	private ResumeButton resume;
	private ResetButton restart;
	private MenuButton menu;
	public static int score;
	public static TrueTypeFont font;
	private Image cocchino;
	private FadeOutTransition fadeout;
	private boolean time = false;
	protected Input keyInput;
	protected Input mouseInput;
	private Background background;
	public Tree palm_1, palm_2;
	public static List <Monke> _99scimmie = new LinkedList <Monke>();
	public static Camera worldCam;
	private int id;
	public static Banana banana;
	private Player frank;
	private Timer timer;
	public static int countGorilla;
	public static World world;
	public static Spawner spawn_1, spawn_2;
	public static float difficulty=0.0f;
	public static boolean gameOver;
	public Level(int stateId) {
		this.id = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		gameOver = false;
		score =0;
		fadeout = new FadeOutTransition(Color.black, 2500);
		cocchino = new Image ("res/cocco_left.png");
		countGorilla=0;
		difficulty = 0.0f;
		Assets.init();
		Assets.initLeaves();
		Assets.initButtons();
		timer = new Timer();
		keyInput= gc.getInput();
		keyInput.addKeyListener((KeyListener) Game.getKeyManager());
		mouseInput= gc.getInput();
		mouseInput.addMouseListener((MouseListener) Game.getMouseManager());

		background = new Background();
		world = new World();
		worldCam = new Camera(world.getWorldWidth(),world.getWorldHeight(),Game.WIDTH,Game.HEIGHT);

		palm_1 = new Tree ( 8*Tile.tilewidth , world.findY(8*Tile.tileheight), 3*Tile.tilewidth, 5*Tile.tileheight);
		palm_2 = new Tree ( 26* Tile.tilewidth, world.findY(26*Tile.tileheight), 3*Tile.tilewidth, 5*Tile.tileheight);

		spawn_1 = new Spawner(1,world.findY(1), 2*Tile.tilewidth, 3*Tile.tileheight);
		spawn_2 = new Spawner(world.getWorldWidth()-1,world.findY(world.getWorldWidth()-1), 2*Tile.tilewidth, 3*Tile.tileheight);

		_99scimmie.clear();
		frank = new Player (17*Tile.tilewidth,world.findY(17*Tile.tileheight)-16, 56, 70);
		_99scimmie.add(0, frank);
		banana = new Banana(Game.WIDTH/2,346, Tile.tilewidth, Tile.tileheight);

		resume= new ResumeButton(gc, Assets.start, (Game.WIDTH/2)-75, (Game.HEIGHT/2)-25, 150, 150);
		restart = new ResetButton(gc, Assets.reset, (Game.WIDTH/2)-275, (Game.HEIGHT/2)-25, 150, 150);
		menu = new MenuButton(gc, Assets.menu, (Game.WIDTH/2)+125, (Game.HEIGHT/2)-25, 150, 150);

		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/edosz.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(52f); // set font size
			font= new TrueTypeFont(awtFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Game.getKeyManager().update();
		Game.getMouseManager().update();
		if(!gc.isPaused()) {
			background.update();
			frank.update();
			if(Player.isShooting)frank.weapon.update();
			palm_1.update();
			palm_2.update();
			timer.update();
			if(timer.checkTimer((int) (6-difficulty))) {
				if(!spawn_1.hasGenerated)spawn_1.spawnEnemy();
			}else spawn_1.hasGenerated =false;

			if(timer.checkTimer((int) (7-difficulty))) {
				if(!spawn_2.hasGenerated)spawn_2.spawnEnemy();
			}else spawn_2.hasGenerated =false;

			if(timer.checkTimer(10)) {
				if(!time) {
					difficulty+=0.2f;
					time = true;
				}
			}else time =false;


			for(int i = 1;i<=countGorilla;i++) {
				_99scimmie.get(i).update();
			}
			banana.update();
			spawn_1.update();
			spawn_2.update();
			worldCam = frank.cam;
			if(Game.getKeyManager().esc)gc.pause();

		}else {
			if(!gameOver) {
				if(resume.update()) {
					System.out.println(System.currentTimeMillis());
					System.out.println(timer.getTimePassed());

					//timer.setStartTime(timer.getTimePassed());
					gc.resume();
					timer.setStartTime(System.currentTimeMillis() - timer.getTimePassed());
				}
				if(restart.update()) {
					for(int i =0 ; i<= countGorilla; i++) {
						_99scimmie.remove(i);
						countGorilla--;
					}
					sbg.getState(getID()).init(gc, sbg);
					gc.resume();
				}
				if(menu.update()) {
					for(int i =0 ; i<= countGorilla; i++) {
						_99scimmie.remove(i);
						countGorilla--;
					}
					gc.reinit();
					gc.resume();
				}
			}
		}
		if(gameOver) {
			fadeout.update(sbg, gc, delta);
			if(fadeout.isComplete()) {
				if(Game.getMouseManager().left) {
					gameOver();
					sbg.addState(new LeadBoard(Game.leadboard));
					sbg.getState(Game.leadboard).init(gc, sbg);
					leave(gc, sbg);
					sbg.enterState(Game.leadboard);
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.render(g);
		g.translate(-worldCam.getCamX(), 0);
		palm_1.render(g);
		palm_2.render(g);
		
		g.translate(worldCam.getCamX(), 0);
		g.translate(-worldCam.getCamX(), 0);
		banana.render(g);		
		for(int i =0;i<=countGorilla;i++) {
			_99scimmie.get(i).render(g, gc.isPaused());
		}
		spawn_1.render(g);
		spawn_2.render(g);
		world.render(g);
		if(Player.isShooting)frank.weapon.render(g);
		g.translate(worldCam.getCamX(), 0);
		//timer
		g.drawImage(Assets.blank, -50, 30 , 300, 210, 0,0, 501,198);

		font.drawString(Game.WIDTH/2 -48, 32, timer.getMinutes() + " : " +timer.getSeconds(), Color.black );
		font.drawString(Game.WIDTH/2 -50, 30, timer.getMinutes() + " : " +timer.getSeconds(), Color.white );
		//score
		g.drawImage(Assets.blank, Game.WIDTH +50, 30 , Game.WIDTH - 300, 210, 0,0, 501,198);

		font.drawString(Game.WIDTH-278, 77, "SCORE: "+ score, Color.black );
		font.drawString(Game.WIDTH-280, 75, "SCORE: "+ score, Color.white );
		//ammo
		if(frank.getAmmo() == 0) {
			font.drawString(127, 77, frank.getAmmo() + "/15", Color.white );
			font.drawString(125, 75, frank.getAmmo() + "/15", Color.red );
		}else {
			font.drawString(127, 77, frank.getAmmo() + "/15", Color.black );
			font.drawString(125, 75, frank.getAmmo() + "/15", Color.white );
		}
		
		g.drawImage(cocchino, 20, 60, 110, 150, 0, 0, 64, 64);

		if(gc.isPaused() && !gameOver) {
			g.drawImage(Assets.darken, 0, 0);
			g.drawImage(Assets.pause, Game.WIDTH/2 - Assets.pause.getWidth()/2, Game.HEIGHT/2 - Assets.pause.getHeight()-60);
			resume.render(GUI, g);
			restart.render(GUI, g);
			menu.render(GUI, g);
		}
		fadeout.postRender(sbg, gc, g);
		if(gameOver)g.drawImage(Assets.lose, Game.WIDTH/2 - Assets.lose.getWidth()/2, Game.HEIGHT/2 - Assets.lose.getHeight()/2);
		if(fadeout.isComplete()) g.drawString("Click anywhere to continue.", Game.WIDTH/2 -120, Game.HEIGHT-30);
	}

	@Override
	public int getID() {
		return id;
	}

	public void gameOver() {
		setList();
		File scoreFile = new File("highscore.txt");
		if(!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = JOptionPane.showInputDialog("Enter your name:");
		System.out.println(records.size());
		for(int i = 0; i<=records.size(); i++) {
			if(i==records.size()) {
				records.add(i, name + ":" + score);
				break;
			}
			if(score > Integer.parseInt(records.get(i).split(":")[1]) ) {
				records.add(i, name + ":" + score);
				break;
			}
			if(score == Integer.parseInt(records.get(i).split(":")[1]) ) {
				if(name.compareTo(records.get(i).split(":")[0])<=0) {
					records.add(i, name + ":" + score);
					break;
				}
			}
		}
		if(records.size() == 0) {
			records.add(0, name + ":" + score);
		}


		FileWriter writeFile = null;
		BufferedWriter writer =null;
		try {
			writeFile = new FileWriter (scoreFile);
			writer = new BufferedWriter(writeFile);	
			writer.flush();
			for(int i = 0; i<10 ; i++) {
				if(i==records.size()) break;
				writer.append(records.get(i) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		records.clear();		
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


}
