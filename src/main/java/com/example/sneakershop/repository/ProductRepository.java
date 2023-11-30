package com.example.sneakershop.repository;

import com.example.sneakershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where name like %?1%" + "or description like %?1% or price like %?1%", nativeQuery = true)
    List<Product> findAllBySearch(String search);

    @Query(value = "select * from product order by price desc ", nativeQuery = true)
    List<Product> findAllByPriceDesc();

    @Query(value = "select * from product order by name asc ", nativeQuery = true)
    List<Product> findAllByNameDesc();

    @Query(value = "select * from product order by substring_index(description, ' - ', -1) asc ", nativeQuery = true)
    List<Product> findAllByColorAsc();
}
