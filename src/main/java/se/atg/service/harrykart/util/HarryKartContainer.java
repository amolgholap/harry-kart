package se.atg.service.harrykart.util;


public class HarryKartContainer implements Comparable<HarryKartContainer>{

	private int position;
	private int points;
	private int pointsInLoop;
	private String horse;
	private int lane;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}
	@Override
	public int compareTo(HarryKartContainer ranking) {
		if(this.pointsInLoop!=ranking.getPointsInLoop()) {
			return this.pointsInLoop-ranking.getPointsInLoop();
		}else {
		return this.points-ranking.getPoints();
		}
	}
	@Override
	public boolean equals(Object o) {
		return true;
	}
	
	@Override
	public int hashCode() {
		return horse.hashCode();
	}
	public int getLane() {
		return lane;
	}
	public void setLane(int lane) {
		this.lane = lane;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPointsInLoop() {
		return pointsInLoop;
	}
	public void setPointsInLoop(int pointsInLoop) {
		this.pointsInLoop = pointsInLoop;
	}
}
