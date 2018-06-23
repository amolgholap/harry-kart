package se.atg.service.harrykart.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.RankingKart;
import se.atg.service.harrykart.services.PlayKartService;

@RestController
@RequestMapping("/api")
public class HarryKartController {

	@Autowired
	PlayKartService playKartService;
	
    @RequestMapping(method = RequestMethod.POST, path = "/play", consumes = "application/xml", produces = "application/json")
    public RankingKart playHarryKart(@RequestBody HarryKart harryKart) {
    	//int numberOfLoops=harryKart.getNumberOfLoops();
    	
        return playKartService.getRanking(harryKart);
    }

}
