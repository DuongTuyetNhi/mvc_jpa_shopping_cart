package shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shoppingcart.entity.ProductEntity;
import shoppingcart.repository.OrderDetailsRepository;
import shoppingcart.repository.OrderRepository;
import shoppingcart.repository.ProductRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class CartController {
    @Autowired
    ProductRepository productRepository;


    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId, HttpSession session) {
        ProductEntity productEntity = productRepository.findById(productId).orElse(null);
        if (productEntity != null) {
            // Lấy thông tin giỏ hàng từ session
            // Get data from session
//            List<CartSession> cartList = (List<CartSession>) request.getSession().getAttribute("cartList");

            List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");
            if (cartList == null) {
                cartList = new ArrayList<>();
            }

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            boolean exists = false;
            for (CartSession cartItem : cartList) {
                if (cartItem.getProductEntity().getProductId() == productId) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                // Nếu sản phẩm chưa có trong giỏ hàng, thêm sản phẩm mới
                CartSession newCartItem = new CartSession(productEntity, 1);
                cartList.add(newCartItem);
            }

            // Cập nhật giỏ hàng trong session
            session.setAttribute("cartList", cartList);
        }
        return "order/Cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable("productId") int productId, HttpSession session) {
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");
        if (cartList != null) {
            cartList.removeIf(cartItem -> cartItem.getProductEntity().getProductId() == productId);
            session.setAttribute("cartList", cartList);
        }
        return "redirect:/myCart"; // Điều hướng người dùng đến trang giỏ hàng
    }


    @GetMapping("/myCart")
    public String viewCart(HttpSession session, Model model) {
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");
        model.addAttribute("cartList", cartList);
        return "order/Cart";
    }
}
