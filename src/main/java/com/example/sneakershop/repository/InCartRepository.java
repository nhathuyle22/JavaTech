package com.example.sneakershop.repository;

import com.example.sneakershop.model.InCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InCartRepository extends JpaRepository<InCart, Integer> {

    @Query(value = "select distinct * from in_cart where idCart = ?1 and idProduct = ?2", nativeQuery = true)
    InCart findAllByCartIdAndPhoneId(int idCart, int idProduct);

    @Transactional
    @Modifying
    @Query(value = "delete from in_cart where idCart = ?1", nativeQuery = true)
    void deleteAllByIdCart(int idCart);
}
