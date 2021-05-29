package com.example.advertisementapp.web;

import com.example.advertisementapp.Exception.AdvertisementException;
import com.example.advertisementapp.Exception.UserException;
import com.example.advertisementapp.dto.AdvertisementDto;
import com.example.advertisementapp.dto.UserDto;
import com.example.advertisementapp.service.AdvertisementService;
import com.example.advertisementapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    @Autowired
    public AppController(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("userDto", new UserDto());
        return "index";
    }

    @GetMapping("/login-form")
    public String loginPage(Model model){
        return homePage(model);
    }

    @PostMapping("/user-login")
    public String login(@ModelAttribute("userDto") UserDto userDto){
        try {
            userService.login(userDto);
        } catch (UserException e) {
            return "redirect:/index?error";
        }
        return "redirect:/dashboard?success";

    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto){
        try {
            userService.createUser(userDto);
        } catch (UserException e) {
            return "redirect:/register?error";
        }
        return "redirect:/register?success";
    }



}
