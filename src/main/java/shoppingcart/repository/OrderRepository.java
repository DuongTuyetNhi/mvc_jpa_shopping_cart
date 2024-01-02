package shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppingcart.entity.OrderEntity;
import shoppingcart.entity.ProductEntity;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAll();

    OrderEntity findByOrderId(int orderId);

    List<OrderEntity> findByCustomerName(String customerName);

    List<OrderEntity> findByCustomerAddress(String customerAddress);

}
