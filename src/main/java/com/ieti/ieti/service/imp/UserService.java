package com.ieti.ieti.service.imp;


import com.ieti.ieti.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements com.ieti.ieti.service.UserService {
    private HashMap<String,User> userPersistence =new HashMap<String,User>();



    @Override
    public User create(User user) {
        User userCreated=new User(user.getId(),user.getName(),user.getEmail(),user.getLastName());
        userPersistence.put(userCreated.getId(),userCreated);
        return userCreated;
    }


    @Override
    public User findById(String id) {
        User foundUser=null;
        foundUser= userPersistence.get(id);
        return foundUser;
    }

    @Override
    public List<User> all() {
        ArrayList<User> usersList=new ArrayList<User>(userPersistence.values());
        return usersList;

    }

    @Override
    public void deleteById(String id) {
        userPersistence.remove(id);

    }

    @Override
    public User update(User user, String userId) {
        userPersistence.put(user.getId(),user);
        return user;
    }
}
