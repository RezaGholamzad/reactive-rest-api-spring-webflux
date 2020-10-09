package com.dzone.reactiverestapis.service;

import com.dzone.reactiverestapis.model.Product;
import com.dzone.reactiverestapis.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<Product> getAllProducts(){
        return repository.findAll();
    }

    public Mono<Product> save(Product product){
        product.setId(new Random().nextInt());
        return repository.save(product);
    }

    public Mono<Void> deleteProduct(int id){
        return repository.deleteById(id);
    }

    public Mono<ResponseEntity<Product>> update(Product product){

        /*
            map() is used for transformation only, but flatMap() is used
            for both transformation and flattening.
            The map() method produces one output value for each input value in the stream.
            So if there are n elements in the stream, map() operation will produce a stream
            of n output elements.

            flatMap() is two step process i.e. map() + Flattening.
            It helps in converting Collection<Collection<T>> to Collection<T>.

            Mono<ResponseEntity<Mono<Product>>> to Mono<ResponseEntity<Product>>
         */
        return repository.findById(product.getId()).flatMap(p -> {
            System.out.println(p.getId());
            System.out.println(p.getTitle());
            p.setTitle(product.getTitle());
            return repository.save(p);
        })
        .map(ResponseEntity::ok)
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
