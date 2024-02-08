package com.kh.demo.domain.product.svc;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service //SVC 역할을 하는 클래스
public class ProductSVCImpl implements ProductSVC{

  private ProductDAO productDAO;

  ProductSVCImpl(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Optional<Product> findById(Long productId) {
    return productDAO.findById(productId);
  }
}
