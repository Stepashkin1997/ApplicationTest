package ru.eltex.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String authorization(ModelMap model, HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }

        return "authorization";
    }

    @PostMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
