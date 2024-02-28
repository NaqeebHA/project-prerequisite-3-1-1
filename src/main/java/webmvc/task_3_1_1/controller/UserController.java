package webmvc.task_3_1_1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webmvc.task_3_1_1.model.User;
import webmvc.task_3_1_1.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userForm", new User());
        List<User> userList= userService.getUserList();
        model.addAttribute("users", userList);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        userService.addUser(user);
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String editUser(@RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
        model.addAttribute("editUserForm", new User());
        model.addAttribute("id", id);
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edited")
    public String postEditUser(@ModelAttribute User newUser, @RequestParam("id") Long id, Model model) {
        userService.updateUserById(id, newUser.getFirstName(), newUser.getLastName(), newUser.getEmail());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/";
    }
}

