package se.atg.service.harrykart.model;


import java.util.ArrayList;

//@XmlRootElement(name="harryKart")
//@XmlAccessorType(XmlAccessType.FIELD)
public class HarryKart {

	private int numberOfLoops;
	
	private ArrayList<Participant> startList;
	
	private ArrayList<Loop> powerUps;
	public int getNumberOfLoops() {
		return numberOfLoops;
	}
	public void setNumberOfLoops(int numberOfLoops) {
		this.numberOfLoops = numberOfLoops;
	}
	public ArrayList<Participant> getStartList() {
		return startList;
	}
	public void setStartList(ArrayList<Participant> startList) {
		this.startList = startList;
	}
	public ArrayList<Loop> getPowerUps() {
		return powerUps;
	}
	public void setPowerUps(ArrayList<Loop> powerUps) {
		this.powerUps = powerUps;
	}
	
}
