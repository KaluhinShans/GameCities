package sprboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprboot.entity.City;
import sprboot.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public City findById(long i) {
        return cityRepository.findById(i).get();
    }

    public boolean isCityReal(String city) {
        if (cityRepository.findByName(city) == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<City> cityFromLetter(String letter){
        return cityRepository.findCityLikeLetter(letter);
    }
}
