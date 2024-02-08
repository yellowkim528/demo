package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Slf4j
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
//    template.update(sql.toString(),param,keyHolder,new String[]{"product_id"});
    template.update(sql.toString(),param,keyHolder,new String[]{"product_id","pname","quantity"});
//    long productId = keyHolder.getKey().longValue(); //상품아이디
//    log.info("keyHolder={}", keyHolder.getKeys());
    Long product_id = ((BigDecimal)keyHolder.getKeys().get("product_id")).longValue(); //상품아이디
    return product_id;
  }

  @Override
  public Optional<Product> findById(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id,pname,quantity,price,cdate,udate ");
    sql.append("  from product ");
    sql.append(" where product_id = :productId ");

    try {
      Map<String,Long> map = Map.of("productId",productId);
      Product product = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(Product.class));
      return Optional.of(product);

    }catch (EmptyResultDataAccessException e){
      //조회결과가 없는경우
      return Optional.empty();
    }
  }
}
