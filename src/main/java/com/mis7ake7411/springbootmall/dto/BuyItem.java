package com.mis7ake7411.springbootmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyItem {
  @NonNull
  private Integer productId;
  @NonNull
  private Integer quantity;
}
