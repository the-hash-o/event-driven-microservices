package com.appsdeveloperblog.estore.productsservice.data.repo

import com.appsdeveloperblog.estore.productsservice.data.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<ProductEntity, UUID> {
    fun findByProductId(productId: UUID): MutableList<ProductEntity>
    fun findByProductIdOrTitle(productId: UUID, title: String): ProductEntity?
}