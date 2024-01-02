package shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppingcart.entity.ProductEntity;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Override
    Iterable<ProductEntity> findAll();



    ProductEntity findByProductId(int productId);

    List<ProductEntity> findByProductName(String productName);

}
