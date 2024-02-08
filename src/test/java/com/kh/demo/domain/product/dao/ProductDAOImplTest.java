package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest  // springboot테스트환경 실행
class ProductDAOImplTest {

  @Autowired  // springboot 컨테이너의 객체를 주입 받는다.
  ProductDAO productDAO;
  
  @Test
  @DisplayName("상품등록")
  void save() {
    Product product = new Product();
    product.setPname("노트북");
    product.setQuantity(3L);
    product.setPrice(2_000_000L);

    Long productId = productDAO.save(product);
    log.info("productId={}{}", productId,"2");
  }

  @Test
  @DisplayName("상품조회")
  void findById() {
    Long productId = 1L;
    Optional<Product> findedProduct = productDAO.findById(productId);
    Product product = findedProduct.orElse(null);
    log.info("product={}", product);
  }
  
  @Test
  @DisplayName("상품목록")
  void findAll(){
    List<Product> list = productDAO.findAll();
    for (Product product : list) {
      log.info("product={}",product);
    }
    log.info("size={}",list.size());
  }
}