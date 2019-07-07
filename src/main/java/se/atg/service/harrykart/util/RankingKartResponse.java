package se.atg.service.harrykart.util;

import java.util.List;

public class RankingKartResponse implements Response{
private List<HorseRankings> ranking;

public List<HorseRankings> getRanking() {
	return ranking;
}

public void setRanking(List<HorseRankings> ranking) {
	this.ranking = ranking;
}

}
