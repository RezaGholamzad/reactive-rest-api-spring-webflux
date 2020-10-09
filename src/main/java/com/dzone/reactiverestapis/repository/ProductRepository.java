package com.dzone.reactiverestapis.repository;

import com.dzone.reactiverestapis.model.Product;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface ProductRepository extends ReactiveCassandraRepository<Product, Integer> {
}
