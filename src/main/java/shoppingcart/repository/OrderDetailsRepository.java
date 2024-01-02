package shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppingcart.entity.OrderDetailsEntity;
import shoppingcart.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity, Integer> {
    List<OrderDetailsEntity> findAll();

}
