package se.atg.service.harrykart.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

//@XmlRootElement(name="loop")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Loop {
	
    private String number;
	
	
	private ArrayList<Lane> lane = new ArrayList<>();
	
	public ArrayList<Lane> getLane() {
		return lane;
	}

	public void setLane(Lane lane) {
		this.lane.add(lane);
	}
	@XmlAttribute
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

//	public Lane getLane() {
//		return lane;
//	}
//
//	public void setLane(Lane lane) {
//		this.lane = lane;
//	}
	
}
