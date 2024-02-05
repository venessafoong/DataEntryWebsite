package com.venessa.springwebproject.APILayer;

import com.venessa.springwebproject.Model.User;
import com.venessa.springwebproject.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String goToIndex()
    {
        return "index";
    }

    @GetMapping("/register")
    public String goToRegisterPage(User user)
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(User user)
    {
        if(userService.registerUser(user))
        {
            return "redirect:/login";
        } else {
            return "register";
        }
    }

    @GetMapping("/login")
    public String goToLoginPage(User user)
    {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(User user, Model model)
    {
        if (userService.verifyUserCredentials(user.getUsername(), user.getPassword())) {
            //session.setAttribute("current_user", username);
            return "redirect:/home/" + user.getUsername();
        } else
        {
            model.addAttribute("error","Wrong username or password");
            return "login";
        }
    }

    @GetMapping("/home/{username}")
    public String goToHomePage(@PathVariable("username") String username, Model model)
    {
        return "home";
    }

}
