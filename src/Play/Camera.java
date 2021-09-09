package Play;
public class Camera {
	public int offsetMaxX;
	public int offsetMaxY;
	public int offsetMinX;
	public int offsetMinY;
	
	public int camSizeX;
	public int camSizeY;
	
	public int camX;
	public int camY;
	
	public Camera(int worldSizeX, int worldSizeY, int viewPortSizeX, int viewPortSizeY) {
		this.camSizeX = viewPortSizeX;
		this.camSizeY = viewPortSizeY;
		this.offsetMaxX = worldSizeX - viewPortSizeX;
		this.offsetMaxY = worldSizeY - viewPortSizeY;
		this.offsetMinX =0;
		this.offsetMinY =0;
		
	}
	
	public void update() {
		if(camX>offsetMaxX) camX = offsetMaxX;
		else if(camX<offsetMinX) camX = offsetMinX;
		if(camY>offsetMaxY) camY = offsetMaxY;
		else if(camY<offsetMinY) camY = offsetMinY;
	}

	public void setCamX(int camX) {
		this.camX = camX - camSizeX/2;
	}

	public void setCamY(int camY) {
		this.camY = camY - camSizeY/2;
	}
	
	public int getCamX() {
		return camX;
	}
	public int getCamY() {
		return camY;
	}
}
