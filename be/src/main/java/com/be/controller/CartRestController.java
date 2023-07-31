package com.be.controller;

import com.be.dto.CartDetailDto;
import com.be.dto.ICartDetailDto1;
import com.be.dto.ICartDto;
import com.be.dto.ICountDto;
import com.be.model.cart.Cart;
import com.be.model.cart.CartDetail;
import com.be.model.product.Product;
import com.be.model.user.User;
import com.be.service.ICartDetailService;
import com.be.service.ICartService;
import com.be.service.IProductService;
import com.be.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-cart")
@CrossOrigin("*")
public class CartRestController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private ICartDetailService iCartDetailService;
    @Autowired
    private IProductService iProductService;

    @GetMapping("/getListCart")
    public ResponseEntity getListCart(@RequestParam("idCustomer") Integer idCustomer,
                                      @PageableDefault(value = 2) Pageable pageable) {
        Page<CartDetailDto> list = iCartService.getListCart(pageable, idCustomer);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@RequestParam("idCustomer") Integer idCustomer,
                                    @RequestParam("idProduct") Integer idProduct,
                                    @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
        ICartDto iCartDto = iCartService.findByCartToIdCustomer(idCustomer);
        Product product = iProductService.findById(idProduct);
        if (product.getQuantity() <= 0) {
            // 500, sản phẩm hết hàng
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (product.getQuantity() - quantity < 0) {
//                    Khách hàng đặt quá số lượng
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        // Trường hợp đã có giỏ hàng rồi
        if (iCartDto != null) {
            ICartDetailDto1 iCartDetailDto1 = iCartDetailService.getCartDetailByIdProduct(idProduct);
            //Trường hợp đã có cart_detail
            if (iCartDetailDto1 != null) {
                if (product.getQuantity() <= 0) {
                    // 500, sản phẩm hết hàng
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (product.getQuantity() - quantity < 0) {
//                    Khách hàng đặt quá số lượng
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                //trường hợp đã có cart_detail
                CartDetail cartDetail = iCartDetailService.findById(iCartDetailDto1.getId());
                cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                cartDetail.setTotal(cartDetail.getQuantity() * product.getPrice());
                iCartDetailService.save(cartDetail);

//                Set lại giá trong cart
                Cart cart = iCartService.findById(iCartDto.getId());
                cart.setTotal(cart.getTotal() + product.getPrice()*quantity);
            }
            //Trường hợp chưa có cart_detail
            if (iCartDetailDto1 == null) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setQuantity(quantity);
                cartDetail.setTotal(quantity * product.getPrice());
                cartDetail.setCart(new Cart(iCartDto.getId()));
                cartDetail.setProduct(new Product(idProduct));
                iCartDetailService.save(cartDetail);

                // Set lại giá trong cart
                Cart cart = iCartService.findById(iCartDto.getId());
                cart.setTotal(cart.getTotal() + (product.getPrice()*quantity));
            }
        }
        //Trường hợp chưa có giỏ hàng
        if (iCartDto == null) {
            //Tạo giỏ hàng cho khách hàng
            User user = iUserService.findById(idCustomer);
            Cart cart = new Cart();
            cart.setCode("KH-" + user.getId());
            cart.setTotal(0.0);
            cart.setCustomerName(user.getName());
            cart.setUser(user);
            iCartService.save(cart);

            //Tạo cart_detail
            ICartDto iCartDto1 = iCartService.findByCartToIdCustomer(idCustomer);

            CartDetail cartDetail = new CartDetail();
            cartDetail.setQuantity(quantity);
            cartDetail.setTotal(quantity * product.getPrice());
            cartDetail.setProduct(product);
            cartDetail.setCart(new Cart(iCartDto1.getId()));
            cartDetail.setProduct(product);
            iCartDetailService.save(cartDetail);

            //set tổng tiền vào cart
            cart.setTotal(cartDetail.getTotal());
            iCartService.save(cart);
        }
        product.setQuantity(product.getQuantity() - quantity);
        iProductService.save(product);


        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/reductionToCart")
    public ResponseEntity reductionToCart(@RequestParam("idCustomer") Integer idCustomer,
                                          @RequestParam("idProduct") Integer idProduct) {
        ICartDto iCartDto = iCartService.findByCartToIdCustomer(idCustomer);
        Product product = iProductService.findById(idProduct);
        ICartDetailDto1 iCartDetailDto1 = iCartDetailService.getCartDetailByIdProduct(idProduct);
        if (iCartDetailDto1 != null) {
            product.setQuantity(product.getQuantity() + 1);
            iProductService.save(product);

            CartDetail cartDetail = iCartDetailService.findById(iCartDetailDto1.getId());
            cartDetail.setQuantity(cartDetail.getQuantity() - 1);
            cartDetail.setTotal(cartDetail.getTotal() - product.getPrice());
            if (cartDetail.getQuantity()<=0){
                iCartDetailService.deleteById(cartDetail.getId());
            } else {
                iCartDetailService.save(cartDetail);
            }
            //Set lại giá tiền ở trong giỏ hàng
            Cart cart = iCartService.findById(iCartDto.getId());
            cart.setTotal(cart.getTotal()-product.getPrice());
            if (cart.getTotal()==0){
                iCartService.deleteById(cart.getId());
            } else {
                iCartService.save(cart);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/payCart")
    public ResponseEntity payCart (@RequestParam("idCustomer") Integer idCustomer){
//        Lấy ra cart của khách hàng dựa vào id của khách hàng
        ICartDto iCartDto = iCartService.findByCartToIdCustomer(idCustomer);
        iCartService.payCart(iCartDto.getId());
        iCartDetailService.payCartDetail(iCartDto.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getCountProduct")
    public ResponseEntity getCountProduct(@RequestParam("idCustomer") Integer idCustomer) {
        ICartDto iCartDto = iCartService.findByCartToIdCustomer(idCustomer);
        ICountDto iCountDto = iCartService.getCountProduct(iCartDto.getId());
        return new ResponseEntity(iCountDto,HttpStatus.OK);
    }
}
