package se.atg.service.harrykart.services;

import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.RankingKart;

public interface PlayKartService {

	public RankingKart getRanking(HarryKart harryKart);
}
