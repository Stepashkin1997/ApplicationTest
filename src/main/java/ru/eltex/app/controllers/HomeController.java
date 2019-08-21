package ru.eltex.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.eltex.app.model.User;
import ru.eltex.app.store.IUserStore;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    final static Logger log = LogManager.getLogger(HomeController.class);

    @Autowired
    IUserStore iUserStore;

    @GetMapping("/")
    public String authorization(ModelMap model, HttpServletRequest httpServletRequest, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index";
        }
        if (httpServletRequest.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "authorization";
    }

    @GetMapping("/index")
    public String index() {
        log.info("Index");
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        log.info("Sign Up");
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user, ModelMap model) {
        log.info("User was sign up");
        if (iUserStore.saveUser(user)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "signup";
        }
    }
}
