package sprboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sprboot.entity.Leaders;

import java.util.List;

public interface LeadersRepository extends JpaRepository<Leaders, Long> {

    @Query(value = "SELECT * FROM leaders ORDER BY score DESC limit 10",
            nativeQuery = true)
    List<Leaders> findTheBestLeaders();

    @Transactional
    @Modifying
    @Query(value = "insert into leaders (name, score) values(?1, ?2)",
            nativeQuery = true)
    void insertLeaders(String name, int score);
}
