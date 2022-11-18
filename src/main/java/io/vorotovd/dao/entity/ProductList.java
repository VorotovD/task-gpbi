package io.vorotovd.dao.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@ToString(exclude = "products")
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(mappedBy = "productList",cascade = CascadeType.ALL,orphanRemoval = true, fetch = EAGER)
    private List<Product> products = new ArrayList<>();

    @Transient
    public void addProduct(Product product) {
        products.add(product);
        product.setProductList(this);
    }

}
