package com.example.sneakershop.controller;

import com.example.sneakershop.model.Account;
import com.example.sneakershop.model.Cart;
import com.example.sneakershop.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AccountService accountService;

    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        return "register";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String Register(@RequestParam("email") String email,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (accountService.checkIfExistEmail(email)) {
            model.addAttribute("emailError", "Email is already exist!");
            return viewRegister(model);
        } else if (accountService.checkIfExistUsername(username)) {
            model.addAttribute("usernameError", "Username is already exist!");
            return viewRegister(model);
        }
        Account account = new Account(0, username, email, password, false, new Cart());
        accountService.save(account);
        session.setAttribute("account",account);
        redirectAttributes.addFlashAttribute("flashMessage", "Welcome to my world!");
        return "login";
    }
}
