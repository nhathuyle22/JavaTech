package com.example.sneakershop.service;

import com.example.sneakershop.model.InCart;
import com.example.sneakershop.repository.InCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InCartService {
    private final InCartRepository inCartRepository;

    @Autowired
    public InCartService(InCartRepository inCartRepository) {
        this.inCartRepository = inCartRepository;
    }

    public List<InCart> findAll() {
        return inCartRepository.findAll();
    }

    public InCart findById(int id) {
        return inCartRepository.findById(id).orElse(null);
    }

    public InCart save(InCart inCart) {
        return inCartRepository.save(inCart);
    }

    public void update(InCart inCart) {
        InCart inCart1 = inCartRepository.findById(inCart.getId()).orElse(null);
        if (inCart1 != null) {
            inCart1.setQuantity(inCart1.getQuantity());
            inCart1.setCart(inCart1.getCart());
            inCart1.setId(inCart1.getId());
            inCartRepository.save(inCart1);
        }
    }

    public List<InCart> findAllByIdCart(int id){
        List<InCart> inCarts = inCartRepository.findAll();
        List<InCart> inCarts1 = new ArrayList<>();
        for (InCart inCart: inCarts) {
            if (inCart.getCart().getId() == id) {
                inCarts1.add(inCart);
            }
        }
        return inCarts1;
    }
    public InCart getInCartById(int id) {
        return inCartRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        inCartRepository.findById(id).ifPresent(inCartRepository::delete);
    }
}
