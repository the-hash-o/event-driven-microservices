package com.appsdeveloperblog.estore.productsservice.data.repo

import com.appsdeveloperblog.estore.productsservice.data.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, String> {
    fun findByProductId(productId: String): MutableList<ProductEntity>
    fun findByProductIdOrTitle(productId: String, title: String): ProductEntity?
}