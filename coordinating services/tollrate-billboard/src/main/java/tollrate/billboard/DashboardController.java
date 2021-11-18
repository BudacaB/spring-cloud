package tollrate.billboard;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController {

    @LoadBalanced
    @Bean // adds it to the Spring Container
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getTollRateBackup")
    @RequestMapping("/dashboard")
    public String GetTollRate(@RequestParam int stationId, Model m) {

//        RestTemplate rest = new RestTemplate();
//        TollRate tr = rest.getForObject("http://localhost:8085/tollrate/" + stationId, TollRate.class);

        TollRate tr = restTemplate.getForObject("http://toll-service/tollrate/" + stationId, TollRate.class);
        m.addAttribute("rate", tr.getCurrentRate());
        System.out.println("this is the result " + m);
        return "dashboard";
    }

    public String getTollRateBackup(@RequestParam int stationId, Model m) {
        System.out.println("Fallback operation called!");

        m.addAttribute("rate", "1.00");
        return "dashboard";
    }
}
