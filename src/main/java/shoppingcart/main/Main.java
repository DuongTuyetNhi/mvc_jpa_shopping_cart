package shoppingcart.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shoppingcart.configuration.JPAConfig;
import shoppingcart.entity.OrderDetailsEntity;
import shoppingcart.entity.OrderEntity;
import shoppingcart.entity.ProductEntity;
import shoppingcart.repository.OrderDetailsRepository;
import shoppingcart.repository.OrderRepository;
import shoppingcart.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderRepository = context.getBean(OrderRepository.class);
    static OrderDetailsRepository orderDetailsRepository = context.getBean(OrderDetailsRepository.class);
    static ProductRepository productRepository = context.getBean(ProductRepository.class);

    public static void main(String[] args) {
        //createNewOrderDetailsEntryWithNewOrder();
//        createNewOrderDetailsEntry();
//        findAll();
        findByProductId(5);
    }

    // Tạo một OrderDetailsEntity mới
    private static OrderDetailsEntity createNewOrderDetail(){
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setQuantity(100);
        return orderDetailsEntity;
    }

    public static void createNewOrderDetailsEntry(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(LocalDate.parse("2022-05-05"));
        orderEntity.setCustomerName("Nguyen Hoa");
        orderEntity.setCustomerAddress("Ha Noi");

        // Lưu OrderEntity trước khi tạo OrderDetailsEntity
        orderRepository.save(orderEntity);

        OrderDetailsEntity orderDetailsEntity = createNewOrderDetail();
        orderDetailsEntity.setOrder(orderEntity);

        // Lưu OrderDetailsEntity
        orderDetailsRepository.save(orderDetailsEntity);
    }

    public static void findAll(){
        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();
        if (productEntityList != null){
            System.out.println("\nFound " + productEntityList.size() + " products");
            for(ProductEntity product: productEntityList){
                System.out.println(product.toString());
            }
        }
    }

    public static void findByProductId(int productId){
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductId(productId));
        if(productEntity.isPresent()){
            System.out.println("Find by ID");
            System.out.println(productEntity.get().toString());
        }
    }
}
