package se.atg.service.harrykart.model;

public class ResponseError implements Response{
private String result;
private String desription;
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getDesription() {
	return desription;
}
public void setDesription(String desription) {
	this.desription = desription;
}
}
