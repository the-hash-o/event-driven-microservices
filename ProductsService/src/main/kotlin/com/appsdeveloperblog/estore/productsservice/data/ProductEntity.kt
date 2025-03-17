package com.appsdeveloperblog.estore.productsservice.data

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "products")
class ProductEntity(
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val productId: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val title: String = "Default Title",
    val price: BigDecimal = BigDecimal(0),
    val quantity: Int = 0
)