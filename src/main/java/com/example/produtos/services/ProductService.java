package com.example.produtos.services;

import com.example.produtos.models.Product;
import com.example.produtos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllByActiveTrue(){
        var allProducts = productRepository.findAllActiveProduct();
        return allProducts;
    }

    public Product findById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()){
            return productOptional.get();
        }else{
            throw new IllegalArgumentException("Produto não encontrado.");
        }
    }

    public Product create(Product product){
        var created = productRepository.save(product);
        return created;
    }

    public Product update(Product product) {
        Optional<Product> updateProduct = productRepository.findById(product.getId());

        if (updateProduct.isPresent()){
            Product productToUpdated = updateProduct.get();

            productToUpdated.setName(product.getName());
            productToUpdated.setPrice_in_cents(product.getPrice_in_cents());

            productToUpdated = productRepository.save(productToUpdated);

            return productToUpdated;
        }else{
            throw new IllegalArgumentException("Produto não encontrado!");
        }
    }

    public void delete(Integer Id){
        Optional <Product> deleteProduct = productRepository.findById(Id);
        if(deleteProduct.isPresent()){
            var product = deleteProduct.get();
            product.setActive(false);

            productRepository.save(product);
        }else{
            throw new IllegalArgumentException("Produto não encontrado!");
        }

    }


}
