package sprboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sprboot.services.CityService;
import sprboot.services.LeadersService;

@SpringBootApplication(scanBasePackages={
        "sprboot.repository", "sprboot.services","sprboot.controller","sprboot.entity"})
public class CitiesApplication {

    @Autowired
    CityService cityService;
    @Autowired
    LeadersService leadersService;

    public static void main(String[] args) {
        SpringApplication.run(CitiesApplication.class, args);
    }

}
