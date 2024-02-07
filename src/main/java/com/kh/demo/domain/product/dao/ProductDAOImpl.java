package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository //dao역할을 하는 클래스
public class ProductDAOImpl implements ProductDAO{

  private final NamedParameterJdbcTemplate template;

  ProductDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Long save(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(product_id,pname,quantity,price) ");
    sql.append("values(product_product_id_seq.nextval, :pname, :quantity, :price) ");

    // SQL파라미터 자동매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(),param,keyHolder,new String[]{"product_id"});
    long productId = keyHolder.getKey().longValue(); //상품아이디
    
    return productId;
  }
}
