package com.kh.demo.domain.pubdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service

public class StockPrice {
  private final WebClient webClient;
  private String baseUrl = "https://apis.data.go.kr";
  private final String serviceKey = "2s8gpTy4cAHLFmocDZh70QO94wQfxdEZMkauGakoluXCgPneCoqhFKJ7KqYE3FChzBbnNQrmtkZXPsJn8QQiGQ==";
  private final String numOfRows = "20";
  private final String pageNo = "1";
  private final String resultType = "json";




  @Autowired
  public StockPrice(WebClient.Builder webClientBilder){

//    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
//    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

    this.webClient = webClientBilder
//            .uriBuilderFactory(factory)
        .baseUrl(baseUrl)
//        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) //json포맷요청
        .build();
  }

  public String reqStockPrice(String keyword, String startDate, String endDate){
    final String itmsNm = keyword;
    final String beginBasDt = startDate;
    final String endBasDt = endDate;

    // http get 요청하면 http 응답메시지 수신
    Mono<String> response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo")                 //베이스url 이하 경로
            .queryParam("serviceKey",serviceKey)
            .queryParam("numOfRows",numOfRows)
            .queryParam("pageNo",pageNo)
            .queryParam("resultType",resultType)
            .queryParam("itmsNm",itmsNm)
            .queryParam("beginBasDt",beginBasDt)
            .queryParam("endBasDt",endBasDt)
//              .queryParam("sort","")                       //sort
            .build())
//        .header("resultCode","00")
//        .header("resultMsg","NORMAL SERVICE.")
        .retrieve()
        .bodyToMono(String.class);
    // http응답메시지 바디를 읽어 문자열로 반환
    String data = response.block();
    return data;
  }
}
