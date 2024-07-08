package com.mis7ake7411.springbootmall.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateOrderDto {
  @NotEmpty
  private List<BuyItem> buyItemList;
}
