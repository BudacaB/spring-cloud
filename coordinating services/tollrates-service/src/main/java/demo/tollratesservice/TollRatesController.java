package demo.tollratesservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
public class TollRatesController {

    @RequestMapping("/tollrate/{stationId}")
    public TollRates GetTollRate(@PathVariable int stationId) {
        TollRates tr;

        System.out.println("Station requested: " + stationId);

        switch(stationId) {
            case 1:
                tr = new TollRates(stationId, new BigDecimal("0.55"), Instant.now().toString());
                break;
            case 2:
                tr = new TollRates(stationId, new BigDecimal("1.05"), Instant.now().toString());
                break;
            case 3:
                tr = new TollRates(stationId, new BigDecimal("0.60"), Instant.now().toString());
                break;
            default:
                tr = new TollRates(stationId, new BigDecimal("1.00"), Instant.now().toString());
                break;
        }
        return tr;
    }
}
