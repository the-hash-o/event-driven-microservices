package com.appsdeveloperblog.estore.productsservice2.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "product-lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLookupEntity implements Serializable {

    @Id
    private String productId;

    @Column(unique = true)
    private String title;
}
