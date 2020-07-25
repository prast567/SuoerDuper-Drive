package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserLogin;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import com.udacity.jwdnd.course1.cloudstorage.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HashService hashService;


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") User user, RedirectAttributes rattrs, Model model) {
        if (userService.getUserByUserName(user.getUserName())) {
            model.addAttribute("message", "fail");
            return "signup";
        } else {
            /* get a random salt and hash the password */
            String salt = userService.generateSalt(8);
            String hashedPassword = hashService.getHashedValue(user.getPassword(), salt);
            /*store random salt into users table */
            user.setSalt(salt);
            /* spring default password encoder */
            user.setPassword(hashedPassword);

            userService.insertUser(user);
            rattrs.addFlashAttribute("message", "success");
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("message") String status, Model model) {
        UserLogin userLogin = new UserLogin();
        model.addAttribute("message", status);
        model.addAttribute("userLogin", userLogin);
        return "login";
    }
}