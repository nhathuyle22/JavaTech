package com.example.sneakershop.controller;

import com.example.sneakershop.model.Account;
import com.example.sneakershop.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LoginController {
    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Account account= accountService.findByUsername(username);
        if (username == null) {
            model.addAttribute("usernameError", "Username is not exist!");
            return viewLogin(model);
        } else if (!account.getPassword().equals(password)) {
            model.addAttribute("passwordError", "Wrong password!");
            return viewLogin(model);
        }
        session.setAttribute("account",account);
        redirectAttributes.addFlashAttribute("flashMess", "Welcome back!");
        return "home";
    }
}
