package com.kh.demo.web;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller // Controller 역할을 하는 클래스
@RequestMapping("/products")    // http://localhost:9080/products
public class ProductController {

  private ProductSVC productSVC;
  ProductController(ProductSVC productSVC){
    this.productSVC = productSVC;
  }

  //상품등록양식
  @GetMapping("/add")         // Get, http://localhost:9080/products/add
  public String addForm() {
    return "product/add";     // view이름  상품등록화면
  }

  //상품등록처리
  @PostMapping("/add")        // Post, http://localhost:9080/products/add
  public String add(
          @RequestParam("pname") String pname,
          @RequestParam("quantity") Long quantity,
          @RequestParam("price") Long price,
          Model model
          ){

    log.info("pname={}, {}, {}", pname,quantity,price);
    //상품등록
    Product product = new Product();
    product.setPname(pname);
    product.setQuantity(quantity);
    product.setPrice(price);

    Long productId = productSVC.save(product);
    log.info("상품번호={}", productId);
//    model.addAttribute("productId", productId);
    //상품조회
    Optional<Product> findedProduct = productSVC.findById(productId);
    product = findedProduct.orElseThrow();

    model.addAttribute("product", product);

    return "product/detailForm"; // 상품조회화면
  }
}
