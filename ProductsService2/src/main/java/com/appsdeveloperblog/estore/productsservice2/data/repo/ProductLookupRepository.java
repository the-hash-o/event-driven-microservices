package com.appsdeveloperblog.estore.productsservice2.data.repo;

import com.appsdeveloperblog.estore.productsservice2.data.entity.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
