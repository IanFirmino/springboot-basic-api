package com.example.produtos.controllers;

import com.example.produtos.models.Product;
import com.example.produtos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getAllProduct(){
        List<Product> allProduct = productService.findAllByActiveTrue();
        return ResponseEntity.ok().body(allProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProductById(@PathVariable Integer id){
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product data){
        try{
            productService.create(data);
            return ResponseEntity.ok().body(data);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi criado!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProdut(@PathVariable Integer id, @RequestBody Product data){
        Product product = productService.update(data);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        try{
            productService.delete(id);
            return ResponseEntity.ok("Produto deletado com sucesso!");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }


}
