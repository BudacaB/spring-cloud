package demospringcloudm4secureui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class ReportController {

    @Autowired
    private WebClient webClient;

    @RequestMapping("/")
    public String loadHome() {
        return "home";
    }
}
