package Tiles;

import java.util.Random;
import java.util.*;
import org.newdawn.slick.geom.*;
import States.Level;
import org.newdawn.slick.Graphics;

public class World {
	private final int width = 35, height = 12;  //map dimension in tiles
	
	public static List <Line> list = new ArrayList<Line>();
	public static int [] vect;
	
	private int [][] mat;
	private Random rand;
	
	private int hStart = 2;

	public World() {
		rand =new Random();
		mat = new int [width][height]; // id: 0-34 -- 0-11
		generate();
		loadWorld();
		list.clear();
		setConstraints();
	}

	public void generate() {
		vect = new int [width];
		for(int i = 0; i<width; i++) {
			vect [i] = rand.nextInt(3);
			if(i==8 || i==26 || i == 7|| i == 25)vect[i]=0;
			if(i>0 && (vect[i]==1 && vect[i-1]==2)) {
				i--;
				continue;
			}
			if(i>0 && (vect[i]==2 && vect[i-1]==1)) {
				i--;
				continue;
			}
			
			
			switch(vect[i]) {
			case 0:		//		_
				break;
			case 1:		//		\		
				if(hStart== 1) {
					i--;
					break;
				}else hStart--;
				break;
			case 2:		//		/
				if(hStart== 3) {
					i--;
					break;
				}else hStart++;
				break;				
			}
		}
	}

	public void loadWorld() {

		for(int y=0;y<height;y++) {
			hStart=8;
			for(int x=0 ; x < width;x++) {
				
				if(y<hStart)mat[x][y]=6; //sky
				else if(y==hStart) {
					mat[x][y] = vect [x];
				}
				else if(y>hStart&&y>0) {
					switch (mat[x][y-1]) {
					case 0:
						mat[x][y] = 5;
						break;
					case 1:
						mat[x][y] = 3;
						break;
					case 2:
						mat[x][y] = 4;
						break;
					default:
						mat[x][y] = 5;
						break;
					}
				}
				if(x+1 != width) {
					if(vect[x] == 0 || vect[x] == 2) {
						if(vect[x+1] == 2) hStart--;
					}
				}				
				if(vect[x] == 1) {
					hStart++;
				}
			}
		}
	}

	public void render (Graphics g) {
		int xStart =Math.max(0,  (Level.worldCam.getCamX() / Tile.tilewidth) - 1);
		int yStart =Math.max(0,  (Level.worldCam.getCamY() / Tile.tileheight) - 1);
		int xEnd = Math.min(width, ((Level.worldCam.getCamX()  + Level.worldCam.camSizeX )/Tile.tilewidth) +1);
		int yEnd = Math.min(height, ((Level.worldCam.getCamY() + Level.worldCam.camSizeY )/Tile.tileheight) +1);
		
		/*
		g.translate(Level.worldCam.getCamX(),0);
		for(int x = 0 ; x<width-1;x++) {
			g.setColor(Color.red);
			g.draw(list.get(x));
		}
		g.translate(-Level.worldCam.getCamX(), 0); */
		
		for(int y=yStart;y<yEnd; y++) {
			for (int x = xStart; x<xEnd;x++) {
				getTile(x,y).render(g, x * Tile.tilewidth, y * Tile.tileheight);
			}
		}
		
	}
	
	public void setConstraints() {
		boolean found = false;
		for(int x = 0; x<width; x++) {
			found = false;
			for (int y=0;y<height; y++) {
				if(found) break;
				switch(mat[x][y]) {
				case 0:
					found = true;
					list.add (new Line (x * Tile.tilewidth, y *Tile.tileheight,(x +1) * Tile.tilewidth, y *Tile.tileheight));
					break;
				case 1:
					found = true;
					list.add (new Line (x * Tile.tilewidth, y *Tile.tileheight,(x +1) * Tile.tilewidth, (y+1) *Tile.tileheight));
					break;
				case 2:
					found = true;
					list.add( new Line (x * Tile.tilewidth, (y+1)*Tile.tileheight,(x +1) * Tile.tilewidth, y *Tile.tileheight));
					break;
				}
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getWorldWidth() {
		return width*Tile.tilewidth;
	}
	public int getWorldHeight() {
		return height*Tile.tileheight;
	}
	
	public void update () {
	}
	
	public float findY(int x) {
		int tilePos = x/Tile.tilewidth;
		float y=0;
		for(int i = 0;i<height;i++) {
			switch(mat[tilePos][i]) {
			case 0:
				y= i*Tile.tileheight;
				break;
			case 1:
				y= ((i)*Tile.tileheight) + x%Tile.tileheight;
				break;
			case 2:
				y= ((i+1)*Tile.tileheight) - x%Tile.tileheight;
				break;
			default:
				continue;
			}
		}
		return y;	
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[mat[x][y]];
		if(t == null)return Tile.cielo;
		return t;
	}


}
