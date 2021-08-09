package demospringcloudm4secureui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ReportController extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext clientContext; // deprecated

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/")
    public String loadHome() {
        return "home";
    }

    @RequestMapping("/reports")
    public String loadReports(Model model) {
        OAuth2AccessToken t = clientContext.getAccessToken();
        System.out.println("Token: " + t.getValue());

        ResponseEntity<ArrayList<TollUsage>> tolls = oAuth2RestTemplate.exchange("http://localhost:9001/services/tolldata", HttpMethod.GET, null, new ParametrizedTypeReference<ArrayList<TollUsage>>(){});

        model.addAttribute("tolls", tolls.getBody());

        return "reports";
    }

    public class TollUsage {

        public String Id;
        public String stationId;
        public String licensePlate;
        public String timestamp;

        public TollUsage() {
        }

        public TollUsage(String id, String stationId, String licensePlate, String timestamp) {
            this.Id = id;
            this.stationId = stationId;
            this.licensePlate = licensePlate;
            this.timestamp = timestamp;
        }
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest().authenticated();
    }
}
