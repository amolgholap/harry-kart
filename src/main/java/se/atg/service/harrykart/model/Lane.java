package se.atg.service.harrykart.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

//@XmlRootElement(name="lane")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Lane {

	@XmlAttribute
    private int number;
	@JacksonXmlText
    private int speed;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
    
    
}
