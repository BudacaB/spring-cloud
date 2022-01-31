package fast.pass.console;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadBalancerConfig {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new TollServiceInstanceListSupplier("fastpass-service");
    }

}

class TollServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    private String serviceId;

    public TollServiceInstanceListSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 50391, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 50441, false)
        ));
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }
}