package com.venessa.springwebproject.ServiceLayer;

import com.venessa.springwebproject.DataAccessLayer.UserRepository;
import com.venessa.springwebproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepo;

    public boolean registerUser(User user) {
        Optional<User> userOptional = userRepo.findByUsername(user.getUsername());

        if(userOptional.isEmpty()) {
            userRepo.save(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean verifyUserCredentials(String username, String password)
    {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if(userOptional.isEmpty()) {
            return false;
        }else {
            return userOptional.get().getPassword().equals(password);
        }
    }

    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user = userRepo.findByUsername(username);
        return user;
    }
}
