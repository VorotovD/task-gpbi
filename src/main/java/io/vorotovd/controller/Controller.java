package io.vorotovd.controller;

import io.vorotovd.dao.entity.Product;
import io.vorotovd.dao.entity.ProductList;
import io.vorotovd.dao.repo.ProductListRepository;
import io.vorotovd.dao.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductListRepository productListRepository;

    @GetMapping("/product/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> result = productRepository.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(result.stream().map(product -> new ProductDto(product.getName(),
                product.getDescription(), product.getKcal(),product.getProductList().getId())).toList(), HttpStatus.OK);
    }

    @GetMapping("/product/list/{listId}")
    public ResponseEntity<List<ProductDto>> getListProducts(@PathVariable long listId) {
        Optional<ProductList> productList = productListRepository.findById(listId);
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(productList.get().getProducts().stream().map(product -> new ProductDto(product.getName(),
                product.getDescription(),product.getKcal(),product.getProductList().getId())).toList(),HttpStatus.OK);

    }

    @PostMapping("/product/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        Optional<ProductList> productListOptional = productListRepository.findById(productDto.getProdListId());
        if (productListOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductListNotFound");
        }
        ProductList productList = productListOptional.get();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setKcal(productDto.getKcal());
        product.setProductList(productList);
        productList.addProduct(product);
        productList = productListRepository.save(productList);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/product/list/{name}")
    public ResponseEntity<?> addList(@PathVariable String name) {
        List<ProductList> productListAll = productListRepository.findAll();
        for (ProductList prodList :productListAll) {
            if(prodList.getName().equals(name)) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("ListAlreadyExist");
            }
        }
        ProductList productList = new ProductList();
        productList.setName(name);
        productList = productListRepository.save(productList);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
