package shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shoppingcart.entity.ProductEntity;
import shoppingcart.repository.OrderRepository;
import shoppingcart.repository.ProductRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(method = GET)
    public String showProducts(Model model){
        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();
        model.addAttribute("productList", productEntityList);

        return "ProductList";
    }

}
