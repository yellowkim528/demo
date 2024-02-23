package com.kh.demo.web;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import com.kh.demo.web.api.ApiResponse;
import com.kh.demo.web.req.ReqSave;
import com.kh.demo.web.req.ResFindById;
import com.kh.demo.web.req.ResSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ApiProductController {

  private final ProductSVC productSVC;

  // 목록
  @ResponseBody
  @GetMapping
  public ApiResponse<?> list() {
    List<Product> list = productSVC.findAll();
    ApiResponse<List<Product>> res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), list);
    res.setTotalCnt(list.size());
    return res;
  }


  // 조회
  @ResponseBody
  @GetMapping("/{pid}")
  public ApiResponse<?> findById(
      @PathVariable("pid") Long productId) {
    log.info("productId={}",productId);

    /* 요청 */
    Product product = new Product();
    Optional<Product> optionalProduct = productSVC.findById(productId);

    /* 응답 */
    ApiResponse<ResFindById> res = null;
    // 상품을 찾은 경우
    if (optionalProduct.isPresent()) {
      Product findedProduct = optionalProduct.get();

      ResFindById resFindById = new ResFindById();
      BeanUtils.copyProperties(findedProduct, resFindById);

      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), resFindById);
    // 상품을 못찾은 경우
    } else {
      String rtDetail = "상품번호 : " + productId + "찾고자하는 상품정보가 없습니다." ;
      res = ApiResponse.createApiResponseDetail(ResCode.fail.getCode(), ResCode.fail.name(), rtDetail, null);
    }
    return res;
  }


  // 등록
  @ResponseBody
  @PostMapping      // GET http://localhost:9080/api/products
  public ApiResponse<?> add(
      // @RequestBody : 요청메세지 바디의 json  포맷 문자열 => 자바객체로 변경
      @RequestBody ReqSave reqSave){
    log.info("reqSave={}", reqSave);
    // 1)유효성검증

    // 2)상품등록처리
//    Product product = new Product();
//    product.setPname(reqSave.getPname());
//    product.setQuantity(reqSave.getQuantity());
//    product.setPrice(reqSave.getPrice());

    // 요청
    // 두 필드가 같은 클래스인 경우 Beanutils 사용가능(자동매핑)
    Product product = new Product();
    BeanUtils.copyProperties(reqSave, product);
    Long productId = productSVC.save(product);

    // 응답
    ResSave resSave = new ResSave(productId, reqSave.getPname());
    String rtDetail = "상품번호" + productId + "가 등록되었습니다.";
    ApiResponse<ResSave> res = ApiResponse.createApiResponseDetail(
        ResCode.OK.getCode(), ResCode.OK.name(), rtDetail, resSave);
    return res;

  }


  //수정
  @ResponseBody
  @PatchMapping("/{pid}")
  public void update(){

  }

  //삭제
  @ResponseBody
  @DeleteMapping("/{pid}")
  public void delete(){

  }

}

