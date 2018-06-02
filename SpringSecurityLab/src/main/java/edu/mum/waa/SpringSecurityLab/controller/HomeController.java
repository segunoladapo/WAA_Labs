package edu.mum.waa.SpringSecurityLab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String redirectPath, Model model){
        model.addAttribute("path", redirectPath);
        return "login";
    }
}
