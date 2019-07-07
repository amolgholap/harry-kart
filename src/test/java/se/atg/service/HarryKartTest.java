package se.atg.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import se.atg.service.harrykart.model.HarryKartType;
import se.atg.service.harrykart.util.RankingKartResponse;
import se.atg.service.harrykart.services.PlayKartService;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "se.atg.service.harrykart")
@SpringBootTest(classes = Config.class)
public class HarryKartTest {

    @Autowired
    private PlayKartService harryKartService;


    @Test
    public void testInput0() {

        JAXBContext jaxbContext;
        RankingKartResponse harryKartResponse =null;
        try {
            jaxbContext = JAXBContext.newInstance(HarryKartType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            HarryKartType harryKart = (HarryKartType) jaxbUnmarshaller.unmarshal(this.getClass().getResourceAsStream("/input_0.xml"));

            harryKartResponse = (RankingKartResponse)harryKartService.playKart(harryKart);



            System.out.println(harryKart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals("TIMETOBELUCKY", harryKartResponse.getRanking().get(0).getHorse());
        Assert.assertEquals("HERCULES BOKO", harryKartResponse.getRanking().get(1).getHorse());
        Assert.assertEquals("CARGO DOOR", harryKartResponse.getRanking().get(2).getHorse());
    }


    @Test
    public void testInput1() {

        JAXBContext jaxbContext;
        RankingKartResponse harryKartResponse =null;
        try {
            jaxbContext = JAXBContext.newInstance(HarryKartType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            HarryKartType harryKart = (HarryKartType) jaxbUnmarshaller.unmarshal(this.getClass().getResourceAsStream("/input_1.xml"));

            harryKartResponse = (RankingKartResponse)harryKartService.playKart(harryKart);



            System.out.println(harryKart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals("WAIKIKI SILVIO", harryKartResponse.getRanking().get(0).getHorse());
        Assert.assertEquals("TIMETOBELUCKY", harryKartResponse.getRanking().get(1).getHorse());
        Assert.assertEquals("HERCULES BOKO", harryKartResponse.getRanking().get(2).getHorse());
    }
}
