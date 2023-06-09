package com.example.produtos.repositories;

import com.example.produtos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Integer> {

    @Query("SELECT p FROM product p WHERE p.Active = 1")
    List<Product> findAllActiveProduct();

}
