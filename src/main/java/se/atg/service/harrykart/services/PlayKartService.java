package se.atg.service.harrykart.services;

import se.atg.service.harrykart.Exception.HarrysKartException;
import se.atg.service.harrykart.model.HarryKartType;
import se.atg.service.harrykart.util.Response;

public interface PlayKartService {

	public Response playKart(HarryKartType harryKart) throws HarrysKartException;
}
