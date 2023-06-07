package com.UST.LoginSignup.Service;

import com.UST.LoginSignup.Model.User;
import com.UST.LoginSignup.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthService {
    @Autowired
    private UserRepository repo;
    private static final SecureRandom secureRandom=new SecureRandom();
    private static final Base64.Encoder base64encoder=Base64.getUrlEncoder();
    public User register(User user) {
        if(checkUserExist(user)==true)
            return null;
        user.setToken(generateToken());
        return repo.save(user);
    }

    private String generateToken(){
        byte[] token =new byte[24];
        secureRandom.nextBytes(token);
        return base64encoder.encodeToString(token);
    }
    private boolean checkUserExist(User user){
        User existingUser=repo.findById(user.getUsername()).orElse(null);
        if(existingUser==null){
            return false;
        }
        return true;
    }
    public User login(User user) {
        User existingUser=repo.findById(user.getUsername()).orElse(null);
        if(existingUser.getUsername().equals(user.getUsername())&& existingUser.getPassword().equals(user.getPassword())
                &&existingUser.getRole().equals(user.getRole())){
            existingUser.setPassword("");
            return existingUser;
        }
        return null;
    }
}
