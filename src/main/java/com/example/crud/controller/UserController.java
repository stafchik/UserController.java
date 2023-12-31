package com.example.crud.controller;


import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }


    @PostMapping("/users/{id}/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/users";
    }


    @PostMapping("/users/{id}/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
