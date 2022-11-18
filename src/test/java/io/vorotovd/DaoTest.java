package io.vorotovd;

import io.vorotovd.dao.entity.Product;
import io.vorotovd.dao.entity.ProductList;
import io.vorotovd.dao.repo.ProductListRepository;
import io.vorotovd.dao.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProductApplication.class)
public class DaoTest {

    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void test() {
        ProductList productList = new ProductList();
        productList.setName("pl");
        Product product = new Product();
        product.setDescription("descr");
        product.setKcal(100);
        product.setName("product1");
        productList.addProduct(product);
        productList = productListRepository.save(productList);
        ProductList productList1 = productListRepository.findById(productList.getId()).orElseThrow();
//        Long productId = productList.getProducts().stream().findFirst().orElseThrow().getId();
//        Product product1 = productRepository.findById(productId).orElseThrow();
//        System.out.println(product1);
    }

}
