package com.appsdeveloperblog.estore.productsservice2.data.repo;

import com.appsdeveloperblog.estore.productsservice2.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findByProductId(String productId);

    ProductEntity findByProductIdOrTitle(String productId, String title);
}