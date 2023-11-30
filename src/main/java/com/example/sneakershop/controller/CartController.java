package com.example.sneakershop.controller;

import com.example.sneakershop.model.Account;
import com.example.sneakershop.model.InCart;
import com.example.sneakershop.service.CartService;
import com.example.sneakershop.service.InCartService;
import com.example.sneakershop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;

    private final CartService cartService;

    private final InCartService inCartService;

    public CartController(InCartService inCartService, ProductService productService, CartService cartService) {
        this.inCartService = inCartService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private String viewCart(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null)
            return "/";

        List<InCart> inCarts = inCartService.findAllByIdCart(account.getCart().getId());
        model.addAttribute("inCart", inCarts);

        int total = inCarts
                .stream()
                .mapToInt(inCart -> inCart.getQuantity() * inCart.getId())
                .sum();
        model.addAttribute("total", total);
        return "cart";
    }
}
