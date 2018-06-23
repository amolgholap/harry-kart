package se.atg.service.harrykart.model;

import java.util.List;

public class RankingKart {
private List<RankingResponse> ranking;

public List<RankingResponse> getRanking() {
	return ranking;
}

public void setRanking(List<RankingResponse> ranking) {
	this.ranking = ranking;
}

}
