package sprboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprboot.entity.Leaders;
import sprboot.repository.LeadersRepository;

import java.util.List;

@Service
public class LeadersService {

    @Autowired
    LeadersRepository leadersRepository;

    public List<Leaders> findTheBestLeader(){
        return leadersRepository.findTheBestLeaders();
    }

    public void insertLeaders(Leaders leaders){
        leadersRepository.insertLeaders(leaders.getName(), leaders.getScore());
    }
}
