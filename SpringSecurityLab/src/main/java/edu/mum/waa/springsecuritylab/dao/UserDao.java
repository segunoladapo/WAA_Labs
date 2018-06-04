package edu.mum.waa.springsecuritylab.dao;

import edu.mum.waa.springsecuritylab.model.Book;
import edu.mum.waa.springsecuritylab.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class UserDao {
    private static int idCount = 0;
    private Map<String, User> users = new HashMap<>();

    public UserDao(){
        addUser(new User(++idCount,"segun","segun","SALES"));
        addUser(new User(++idCount,"sunil","sunil","ACCOUNT"));
        addUser(new User(++idCount,"admin","admin","ADMIN"));

    }

    public User findUser(String username){
       return users.get(username);
    }

    public void addUser(User user){
        users.put(user.getUsername(), user);
    }
}
