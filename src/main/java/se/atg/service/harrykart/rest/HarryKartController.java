package se.atg.service.harrykart.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.Exception.HarrysKartException;
import se.atg.service.harrykart.model.HarryKartType;
import se.atg.service.harrykart.util.Response;
import se.atg.service.harrykart.util.ResponseError;
import se.atg.service.harrykart.services.PlayKartService;

@RestController
@RequestMapping("/api")
public class HarryKartController {
    private static final Logger logger = LoggerFactory.getLogger(HarryKartController.class);
    @Autowired
    PlayKartService playKartService;
    @RequestMapping(method = RequestMethod.POST, path = "/play", consumes = "application/xml", produces = "application/json")
    public Response playHarryKart(@RequestBody HarryKartType harryKart) {
        try {
            return playKartService.playKart(harryKart);
        }catch(HarrysKartException e) {
            ResponseError errorMsg= new ResponseError();
            errorMsg.setResult("Error");
            errorMsg.setDesription(e.getMessage());
            return errorMsg;
        }
    }

}
