package sprboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sprboot.entity.City;
import sprboot.entity.Leaders;
import sprboot.services.CityService;
import sprboot.services.LeadersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HomeController {

    @Autowired
    private CityService cityService;

    @Autowired
    private LeadersService leadersService;

    @GetMapping("/randomCity")
    public Map<String, String> randomCity() {

        long v = ThreadLocalRandom.current().nextLong(723, 836);
        String city = cityService.findById(v).getName();
        String lastLetter = getLastLetter(city);

        HashMap<String, String> map = new HashMap<>();
        map.put("randomCity", city);
        map.put("lastLetter", lastLetter);
        return map;
    }

    @PostMapping("/checkCity")
    public Map<String, String> checkCity(@RequestBody Map<String, String> answer) {
        String lastLetter = answer.get("needLetter");
        String city = answer.get("playerAnswer");
        HashMap<String, String> map = new HashMap<>();

        if (isLastLetterFirstInCity(city, lastLetter)) {
            if (cityService.isCityReal(city)) {
                String letter = getLastLetter(city);
                String newCity = randomCityFromLetter(letter);
                String newLetter = getLastLetter(newCity);
                map.put("newCity", newCity);
                map.put("newLetter", newLetter);
            } else {
                map.put("ERROR", "Такого города я не знаю");
            }
        } else {
            map.put("ERROR", "Первая буква не совпадает");
        }

        return map;
    }


    public String randomCityFromLetter(String letter) {
        List<City> cities = cityService.cityFromLetter(letter);
        int v = ThreadLocalRandom.current().nextInt(1, cities.size());
        return cities.get(v).getName();
    }


    public String getLastLetter(String text) {
        String lastLetter = text.substring(text.length() - 1);
        if (lastLetter.equals("ь") || lastLetter.equals("ы") || lastLetter.equals("й") || lastLetter.equals("ц")) {
            text = text.substring(0, text.length() - 1);
            lastLetter = getLastLetter(text);
        }
        return lastLetter;
    }

    public boolean isLastLetterFirstInCity(String city, String lastLetter) {
        String firstLetter = city.substring(0, 1);
        if (firstLetter.equalsIgnoreCase(lastLetter)) {
            return true;
        } else {
            return false;
        }
    }


    @GetMapping("/getLeaders")
    public Map<String, String> getLeaders(){
        HashMap<String, String> map = new HashMap<>();
        int i = 0;
        for (Leaders leader : leadersService.findTheBestLeader()) {
            map.put(i + "leader", leader.getName());
            map.put(i + "score", String.valueOf(leader.getScore()));
            i++;
        }
        return  map;
    }

    @PostMapping("/createNewLeader")
     public void createNewLeader(@RequestBody Map<String, String> leader){
        Leaders newLeader = new Leaders();
        newLeader.setName(leader.get("name"));
        newLeader.setScore(Integer.valueOf(leader.get("score")));

        leadersService.insertLeaders(newLeader);
    }

}