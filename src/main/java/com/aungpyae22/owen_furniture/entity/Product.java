package com.aungpyae22.owen_furniture.entity;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productType;
    private String productName;

    private BigDecimal productPrice;
    private String productPhotoUrl;
    private String productDescription;
    private String availableProduct;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

}
