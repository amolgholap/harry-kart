package se.atg.service.harrykart.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.atg.service.harrykart.Exception.HarrysKartException;
import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.HorseRankings;
import se.atg.service.harrykart.model.Loop;
import se.atg.service.harrykart.model.RankingDecider;
import se.atg.service.harrykart.model.RankingKartResponse;
import se.atg.service.harrykart.model.Response;
import se.atg.service.harrykart.rest.HarryKartController;
import se.atg.service.harrykart.services.PlayKartService;

@Service
public class PlayKartServiceImpl implements PlayKartService{

	private static final Logger logger = LoggerFactory.getLogger(PlayKartServiceImpl.class);
	
	@Override
	public Response getRanking(HarryKart harryKart) throws HarrysKartException{
		try {
		RankingKartResponse rankingKart= new RankingKartResponse();
		final List<RankingDecider> horseRankingKart= new ArrayList<>();
		harryKart.getStartList().forEach(participant ->{
			RankingDecider horseRank = new RankingDecider();
			horseRank.setHorse(participant.getName());
			horseRank.setLane(participant.getLane());
			horseRank.setPointsInLoop(1);//for first loop all run at same speed
			horseRank.setPoints(participant.getBaseSpeed());//base Speed
			horseRankingKart.add(horseRank);
		});
		for(int i=0;i<(harryKart.getNumberOfLoops()-1);i++) {
			final int loopNumber=i;
			Loop loop= harryKart.getPowerUps().get(loopNumber);
			loop.getLane().forEach(lane->{
				for(RankingDecider hrsRank:horseRankingKart){
					if(lane.getNumber()==hrsRank.getLane()) {
						hrsRank.setPoints(hrsRank.getPoints()+lane.getSpeed());
						if(lane.getSpeed()>0) {
							hrsRank.setPointsInLoop(hrsRank.getPointsInLoop()+1);
						}else if(lane.getSpeed()<0) {
							hrsRank.setPointsInLoop(hrsRank.getPointsInLoop()-1);
						}
						break;
					}
				}
			});
		}
		
		//Rank calculation-CompareTo method will first consider the number according to loops if they are same then it has to be decided on the basis of up and down speed
		horseRankingKart.sort(Comparator.reverseOrder());
		List<HorseRankings> finalRankings= new ArrayList<>();
		for(int i=0;i<3;i++) {
			HorseRankings rnkResp= new HorseRankings();
			rnkResp.setHorse(horseRankingKart.get(i).getHorse());
			rnkResp.setPosition(i);
			finalRankings.add(rnkResp);
		}
		rankingKart.setRanking(finalRankings);
		return rankingKart;
		}catch(Exception e) {
			logger.error(e.getMessage());
			throw new HarrysKartException("Something wrong in Harry's Kart Data xml");
		}
	}

}
