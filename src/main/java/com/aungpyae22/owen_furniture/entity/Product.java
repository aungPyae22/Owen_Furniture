package com.aungpyae22.owen_furniture.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "product's type is require")
    private String ProductType;
    @NotBlank(message = "product's name is require")
    private String ProductName;
    @NotBlank(message = "product's price is require")
    private BigDecimal ProductPrice;
    @NotBlank(message = "product's picture is require")
    private String ProductPhotoUrl;
    @NotBlank(message = "product's description is require")
    private String ProductDescription;
    @NotBlank(message = "product state is require")
    private String availableProduct;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

}
