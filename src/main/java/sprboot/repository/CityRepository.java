package sprboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sprboot.entity.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String cityName);

    @Query(value = "SELECT * FROM CITY WHERE LEFT (name, 1) = ?1",
    nativeQuery = true)
    List<City> findCityLikeLetter(String letter);
}
