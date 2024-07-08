package com.mis7ake7411.springbootmall.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyItem {
  @NotNull
  private Integer productId;
  @NotNull
  private Integer quantity;
}
