package com.example.sneakershop.controller;

import com.example.sneakershop.model.Account;
import com.example.sneakershop.model.Product;
import com.example.sneakershop.service.InCartService;
import com.example.sneakershop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private InCartService inCartService;

    private String view(Model model, HttpSession session, List<Product> products) {
        Account account = (Account) session.getAttribute("account");
        if (account == null)
            return "/";

        int orderInCart = inCartService.findAllByIdCart(account.getCart().getId()).size();
        model.addAttribute("products",products);
        model.addAttribute("orderInCart", orderInCart);
        return "home";
    }

    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        return view(model, session, productService.findAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search, Model model, HttpSession session) {
        return view(model, session, productService.findAllBySearch(search));
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model) {
        return  "/";
    }

    @RequestMapping(value = "/price")
    public String sortByPrice(Model model, HttpSession session) {
        return view(model, session, productService.findAllByPriceDesc());
    }

    @RequestMapping(value = "/name")
    public String sortByName(Model model, HttpSession session) {
        return view(model, session, productService.findAllByNameDesc());
    }

    @RequestMapping(value = "/color")
    public String sortByColor(Model model, HttpSession session) {
        return view(model, session, productService.findAllByColorAsc());
    }
}
