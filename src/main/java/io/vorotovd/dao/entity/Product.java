package io.vorotovd.dao.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinColumn(name = "product_list_id")
    private ProductList productList;
    private String name;
    private String description;
    private int kcal;
}
