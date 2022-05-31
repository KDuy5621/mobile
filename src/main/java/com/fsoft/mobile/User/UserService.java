package com.fsoft.mobile.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository repo;
    public List<User> listAll(){

        return   (List<User>) repo.findAll();
    }

    public void save(User user) {

        repo.save(user);
    }
    public boolean login(String username, String password){
        List<User> userList = listAll();
        boolean check=false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().trim().toLowerCase().equals(username.trim().toLowerCase())) {
                if (userList.get(i).getPassword().equals(password)) {
                    check=true;
                    break;
                }
            }
        }
        return check;
    }
    public int getTypeofuser(String username){
        List<User> userList = listAll();
        int type=0;
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim())){
                type=userList.get(i).getTypeofuserid();
            }
        }
        return type;
    }
}
