package ui.online.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.online.Configuration.SecurityConfig;
import ui.online.Repository.peopleRepository;
import ui.online.entity.peopleEntity;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class peopleService {
    @Autowired
    private peopleRepository peopleRepo;
    @Autowired
    private SecurityConfig security;

    public List<peopleEntity> getALl(){
        log.info("All Data Requested");
        return peopleRepo.findAll();
    }
    public Optional<peopleEntity> getByid(Integer ID){
        log.info("Data of ID: "+ID+" requested");
        return peopleRepo.findById(ID);
    }
    public Optional<peopleEntity> getByname(String name){
        log.info("Data of "+name+" requested");
        return peopleRepo.findByname(name);
    }
    public peopleEntity addPeople(peopleEntity people) throws NoSuchAlgorithmException {
        log.info("New User "+people.getName() +" created");
        List<peopleEntity> all = getALl();
        List<Integer> id = new ArrayList<>();
        for(peopleEntity user: all){
            id.add(user.getId());
        }
        Collections.sort(id);
        if(id.size()==0)
            people.setId(1);
        else
            people.setId(id.get(id.size()-1)+1);
        String password = security.passwordEncode().encode(people.getSecurity());
        people.setSecurity(password);
        return peopleRepo.save(people);
    }
    public peopleEntity updateUser(Integer ID, peopleEntity people) throws NoSuchAlgorithmException {
        peopleEntity existUser = getByid(ID).get();
        if(security.verification(people.getSecurity(), existUser.getSecurity())){
            if(people.getName()!=null)
            existUser.setName(people.getName());
            if(people.getHours()!=null)
            existUser.setHours(people.getHours());
            return peopleRepo.save(existUser);
        }
        return null;
    }
    public void deleteUser(Integer ID) {
        peopleEntity people = getByid(ID).get();
        peopleRepo.delete(people);
    }

}
