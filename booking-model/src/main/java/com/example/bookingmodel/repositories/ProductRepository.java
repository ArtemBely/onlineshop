package com.example.bookingmodel.repositories;

import com.example.bookingmodel.data.entity.ProductEntity;
import com.example.bookingmodel.enums.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findProductEntitiesByCategory(CategoryProduct category);
}
