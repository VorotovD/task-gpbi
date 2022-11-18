package io.vorotovd.dao.repo;

import io.vorotovd.dao.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {
}
