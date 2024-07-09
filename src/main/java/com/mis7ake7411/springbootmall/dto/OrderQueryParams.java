package com.mis7ake7411.springbootmall.dto;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParams {
  private Integer userId;
  private Integer limit;
  private Integer offset;
}
