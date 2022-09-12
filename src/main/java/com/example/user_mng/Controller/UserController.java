package com.example.user_mng.Controller;

import com.example.user_mng.Model.Users;
import com.example.user_mng.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Users user, Model model) {
        model.addAttribute("user", new Users());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepo.save(users);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Users user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);

        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            users.setId(id);
            return "update-user";
        }
        userRepo.save(users);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Users users = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(users);
        return "redirect:/index";
    }
}
