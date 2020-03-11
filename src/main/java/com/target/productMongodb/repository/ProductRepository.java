package com.target.productMongodb.repository;

import com.target.productMongodb.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity,Integer> {
    ProductEntity findByProductId(Integer productId);
}
