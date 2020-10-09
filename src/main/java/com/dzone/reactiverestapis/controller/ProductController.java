package com.dzone.reactiverestapis.controller;

import com.dzone.reactiverestapis.model.Product;
import com.dzone.reactiverestapis.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/products")
    public Flux<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @DeleteMapping("product/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/product/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int id , @RequestBody Product product){
        product.setId(id);
        return productService.update(product);
    }

}
