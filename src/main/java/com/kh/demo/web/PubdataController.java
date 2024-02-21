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
  public String newsSearch(
      @RequestParam("keyword") String keyword
  ) {
    log.info("keyword={}", keyword);
    String data = naverNews.reqNews(keyword);

    return data;
  }

  @GetMapping("/stock")
  public String stock() {
    return "pubdata/stock";
  }
  @ResponseBody
  @GetMapping("/stock/find")
  public String stockFind(
      @RequestParam("keyword") String keyword,
      @RequestParam("startDate") String startDate,
      @RequestParam("endDate") String endDate
  ) {
    log.info("keyword={}", keyword);
    log.info("startDate={}", startDate);
    log.info("endDate={}", endDate);

    startDate = startDate.replace("-","");
    endDate = endDate.replace("-","");

    String data = stockPrice.reqStockPrice(keyword, startDate, endDate);
    return data;
  }


}


