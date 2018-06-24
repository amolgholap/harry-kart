package se.atg.service.harrykart.services;

import se.atg.service.harrykart.Exception.HarrysKartException;
import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.Response;

public interface PlayKartService {

	public Response getRanking(HarryKart harryKart) throws HarrysKartException;
}
