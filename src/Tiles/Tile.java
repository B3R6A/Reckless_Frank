package Tiles;

import org.newdawn.slick.*;

public class Tile {
	
	public static Tile [] tiles = new Tile[7];
	public static Tile piano = new PianoTile(0);
	public static Tile discesa = new DiscesaTile(1);
	public static Tile salita = new SalitaTile(2);
	public static Tile mid_d = new Mid_dTile(3);
	public static Tile mid_s = new Mid_sTile(4);
	public static Tile terra = new TerraTile(5);
	public static Tile cielo = new CieloTile(6);


	protected Image texture;
	protected final int id;
	public static final int tilewidth = 64, tileheight = 64;
	
	public Tile(Image texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}

	
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y);
	}
	
	public int getId() {
		return id;
	}
	
	
}
