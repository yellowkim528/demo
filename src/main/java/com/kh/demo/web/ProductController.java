package com.kh.demo.web;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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
          Model model,
          RedirectAttributes redirectAttributes
          ){

    log.info("pname={}, {}, {}", pname,quantity,price);
    //상품등록
    Product product = new Product();
    product.setPname(pname);
    product.setQuantity(quantity);
    product.setPrice(price);

    Long productId = productSVC.save(product);
    log.info("상품번호={}", productId);

    redirectAttributes.addAttribute("pid",productId);
    return "redirect:/products/{pid}/detail"; // 상품조회화면  302 GET http://서버:9080/products/상품번호/detail
  }

  //조회
  @GetMapping("/{pid}/detail")       //GET http://localhost:9080/products/상품번호/detail
  public String findById(@PathVariable("pid") Long productId, Model model){

    Optional<Product> findedProduct = productSVC.findById(productId);
    Product product = findedProduct.orElseThrow();
    model.addAttribute("product", product);

    return "product/detailForm";
  }

  //삭제
  @GetMapping("/{pid}/del")
  public String deleteById(@PathVariable("pid") Long productId){
    log.info("deleteById={}",productId);

    //1)상품 삭제 -> 상품테이블에서 삭제
    int deletedRowCnt = productSVC.deleteById(productId);
    
    return "redirect:/products";     // GET http://localhost:9080/products/
  }

  
  //목록
  @GetMapping   // GET http://localhost:9080/products
  public String findAll(Model model){

    List<Product> list = productSVC.findAll();
    model.addAttribute("list", list);

    return "product/all";
  }
}
