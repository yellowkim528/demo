package com.kh.demo.domain.product.svc;

import com.kh.demo.domain.entity.Product;

import java.util.Optional;

public interface ProductSVC {
  //등록
  Long save(Product product);

  //조회
  Optional<Product> findById(Long productId);
}
