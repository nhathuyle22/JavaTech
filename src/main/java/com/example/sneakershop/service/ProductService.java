package com.example.sneakershop.service;

import com.example.sneakershop.model.Product;
import com.example.sneakershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void update(Product product) {
        Product product1 = productRepository.findById(product.getId()).orElse(null);
        if (product1 != null) {
            product1.setName(product.getName());
            product1.setImage(product.getImage());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            productRepository.save(product1);
        }
    }

    public void delete(Product product) {
        if (productRepository.findById(product.getId()).orElse(null) != null)
            productRepository.delete(product);
    }

    public List<Product> findAllBySearch(String search) {
        return productRepository.findAllBySearch(search);
    }

    public List<Product> findAllByPriceDesc() {
        return productRepository.findAllByPriceDesc();
    }

    public List<Product> findAllByNameDesc() {
        return productRepository.findAllByNameDesc();
    }

    public List<Product> findAllByColorAsc() {
        return productRepository.findAllByColorAsc();
    }
}
