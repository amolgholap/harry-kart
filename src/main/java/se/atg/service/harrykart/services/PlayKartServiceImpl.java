package se.atg.service.harrykart.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import se.atg.service.harrykart.Exception.HarrysKartException;
import se.atg.service.harrykart.model.HarryKartType;
import se.atg.service.harrykart.model.LoopType;
import se.atg.service.harrykart.model.ParticipantType;
import se.atg.service.harrykart.util.*;

import javax.validation.constraints.NotNull;

@Service
public class PlayKartServiceImpl implements PlayKartService{

	private static final Logger logger = LoggerFactory.getLogger(PlayKartServiceImpl.class);

	@Value("${harryKart.listToBeDisplay}")
	private int listToBeDisplay;

	@Override
	public Response playKart(HarryKartType harryKart) throws HarrysKartException {

		//Init the Horse Kart with respective lane with base speed
		final HashMap<Integer, HarryKartContainer> harryKartHorses= initHorsePositionWithLane(harryKart.getStartList().getParticipant());

		//Collect the points for all horses in each lap
		pointsInEachLoop(harryKart, harryKartHorses);

		final List<HarryKartContainer> rankingKart = new ArrayList(harryKartHorses.values());

		//get Final Standings
		return setStandings(rankingKart);
	}

	private HashMap<Integer, HarryKartContainer> initHorsePositionWithLane(@NotNull List<ParticipantType> participants)throws HarrysKartException{

		final HashMap<Integer, HarryKartContainer> horseKart= new HashMap<>();
		try{
		participants.forEach(participant ->{
			HarryKartContainer horseRank = new HarryKartContainer();
			horseRank.setHorse(participant.getName());
			horseRank.setLane(participant.getLane());
			horseRank.setPointsInLoop(1);//base Speed point
			horseRank.setPoints(participant.getBaseSpeed());//base Speed
			horseKart.put(participant.getLane(),horseRank);
		});
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new HarrysKartException("Playing Kart Not Started well");
		}
		return horseKart;
	}

	private HashMap<Integer, HarryKartContainer> pointsInEachLoop(@NotNull HarryKartType harryKartType, @NotNull final HashMap<Integer, HarryKartContainer> harryKartHorses)throws HarrysKartException{

		try {
			for (int i = 0; i < (harryKartType.getNumberOfLoops() - 1); i++) {
				final int loopNumber = i;
				LoopType loop = harryKartType.getPowerUps().getLoop().get(loopNumber);

				loop.getLane().forEach(lane -> {
					HarryKartContainer harryKartContainer = harryKartHorses.get(lane.getNumber());
					harryKartContainer.setPoints(harryKartContainer.getPoints() + lane.getValue());
					if (lane.getValue() > 0) {
						harryKartContainer.setPointsInLoop(harryKartContainer.getPointsInLoop() + 1);
					} else if (lane.getValue() < 0) {
						harryKartContainer.setPointsInLoop(harryKartContainer.getPointsInLoop() - 1);
					}
				});
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new HarrysKartException("Playing Kart Interrupted");
		}

		return harryKartHorses;
	}

	private RankingKartResponse setStandings(@NotNull List<HarryKartContainer> rankingKart){
		List<HorseRankings> finalRankings= new ArrayList<>();

		rankingKart.sort(Comparator.reverseOrder());
		RankingKartResponse rankingKartResponse= new RankingKartResponse();
		for(int i=0;i<listToBeDisplay && i<rankingKart.size();i++) {
			HorseRankings rnkResp= new HorseRankings();
			rnkResp.setHorse(rankingKart.get(i).getHorse());
			rnkResp.setPosition(i+1);
			finalRankings.add(rnkResp);
		}
		rankingKartResponse.setRanking(finalRankings);
		return rankingKartResponse;
	}

}
