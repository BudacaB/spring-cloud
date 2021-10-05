package demo.fastpasscustomer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RestController
public class FastPassController {

    List<FastPassCustomer> customerList = new ArrayList<FastPassCustomer>();

    public FastPassController() {
        FastPassCustomer fc1 = new FastPassCustomer("100", "Rich Roll", "555-123-4567", new BigDecimal("1.10"));
        FastPassCustomer fc2 = new FastPassCustomer("101", "David Goggins", "555-321-4567", new BigDecimal("2.35"));
        FastPassCustomer fc3 = new FastPassCustomer("102", "Jocko Willink", "555-987-4567", new BigDecimal("0.65"));

        customerList.add(fc1);
        customerList.add(fc2);
        customerList.add(fc3);
    }

    @RequestMapping(path = "/fastpass", params = {"fastpassid"})
    public FastPassCustomer getFastPassById(@RequestParam String fastpassid) {

        Predicate<FastPassCustomer> p = c -> c.getFastPassId().equals(fastpassid);
        return customerList.stream().filter(p).findFirst().get();
    }

    @RequestMapping(path = "/fastpass", params = {"fastpassphone"})
    public FastPassCustomer getFastPassByPhone(@RequestParam String fastpassphone) {

        Predicate<FastPassCustomer> p = c -> c.getFastPassId().equals(fastpassphone);
        return customerList.stream().filter(p).findFirst().get();
    }
}
