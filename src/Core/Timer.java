 package Core;

import org.newdawn.slick.Graphics;

public class Timer {

	long minutes;
	long seconds;
	long startTime;
	long timePassed;

	public Timer () {
		this.startTime = System.currentTimeMillis();
		this.minutes = 0;
	}

	public void update() {
		timePassed = System.currentTimeMillis()-startTime;
		seconds = (timePassed/1000);

		if(seconds == 60) {
			minutes++;
			seconds =0;
			startTime=System.currentTimeMillis();
		}        
	}

	public boolean checkTimer(int t) {
		if(seconds>0 && seconds%t==0)return true;
		else return false;
	}

	public long getTimePassed() {
		return timePassed;
	}

	public void setStartTime(long nStartTime) {
		this.startTime = nStartTime;
	}

	public long getSeconds() {
		return seconds;
	}

	public long getStartTime() {
		return startTime;
	}

	public void render(Graphics g) {
	}

	public long getMinutes() {
		return minutes;
	}
}
