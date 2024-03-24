package webmvc.task_3_1_1.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webmvc.task_3_1_1.model.User;
import webmvc.task_3_1_1.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getUserList());
            return "users";
        }
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
    public String postEditUser(@Valid @ModelAttribute("editUserForm") User newUser, BindingResult bindingResult, @RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("user", userService.getUserById(id));
            return "edit";
        }
        userService.updateUserById(id, newUser.getFirstName(), newUser.getLastName(), newUser.getEmail());
        return "redirect:/edit?id=" + id.toString();
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/";
    }
}

