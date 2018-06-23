package se.atg.service.harrykart.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.Loop;
import se.atg.service.harrykart.model.Ranking;
import se.atg.service.harrykart.model.RankingKart;
import se.atg.service.harrykart.model.RankingResponse;
import se.atg.service.harrykart.services.PlayKartService;

@Service
public class PlayKartServiceImpl implements PlayKartService{

	@Override
	public RankingKart getRanking(HarryKart harryKart) {
		RankingKart rankingKart= new RankingKart();
		final List<Ranking> horseRankingKart= new ArrayList<>();
		harryKart.getStartList().forEach(participant ->{
			Ranking horseRank = new Ranking();
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
				for(Ranking hrsRank:horseRankingKart){
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
		
		//position calculation
		horseRankingKart.sort(Comparator.reverseOrder());
		List<RankingResponse> finalRankings= new ArrayList<>();
		for(int i=0;i<3;i++) {
			RankingResponse rnkResp= new RankingResponse();
			rnkResp.setHorse(horseRankingKart.get(i).getHorse());
			rnkResp.setPosition(i);
			finalRankings.add(rnkResp);
		}
		rankingKart.setRanking(finalRankings);
		return rankingKart;
	}

}
