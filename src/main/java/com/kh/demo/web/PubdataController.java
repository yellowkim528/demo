package com.kh.demo.web;

import com.kh.demo.domain.pubdata.NaverNews;
import com.kh.demo.domain.pubdata.StockPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/pubdata")
@RequiredArgsConstructor   // 멤버필드의 final이 붙어있는 필드를 매개변수로 하는 생성자 자동 생성
//@AllArgsConstructor      // 모든 멤버필드 ~ 생성자
//@NoArgsConstructor       // 매개변수 없는 생성자
public class PubdataController {

  private final NaverNews naverNews;
  private final StockPrice stockPrice;

//  @Autowired
//  public PubdataController(NaverNews naverNews) {
//    this.naverNews = naverNews;
//  }

  @GetMapping("/news")        // get http://localhost:9080/pubdata/news
  public String news() {
    return "pubdata/news";
  }

  @ResponseBody
  @GetMapping("/news/search")
  public String search(
      @RequestParam("keyword") String keyword,
      @RequestParam("start") int start,
      @RequestParam("display") int display
  ){
    log.info("keyword={}",keyword);
    String data = naverNews.reqNews(keyword,start,display);
    return data;
  }

  @GetMapping("/stock")
  public String stock() {
    return "pubdata/stock";
  }
  @ResponseBody
  @GetMapping("/stock/find")
  public String stockFind(
      @RequestParam("itmsNm") String itmsNm,
      @RequestParam("beginBasDt") String beginBasDt,
      @RequestParam("endBasDt") String endBasDt,
      @RequestParam("numOfRows") int numOfRows,
      @RequestParam("pageNo") int pageNo
  ) {
//    log.info("itmsNm={}", itmsNm);
//    log.info("beginBasDt={}", beginBasDt);
//    log.info("endBasDt={}", endBasDt);

    beginBasDt = beginBasDt.replace("-","");
    endBasDt = endBasDt.replace("-","");

    String data = stockPrice.reqStockPrice(itmsNm, beginBasDt, endBasDt, numOfRows, pageNo);
    return data;
  }


}


