package com.kh.demo.web.req;

import lombok.Data;

@Data
public class ReqUpdate {
  private String pname;
  private Long quantity;
  private Long price;
}
