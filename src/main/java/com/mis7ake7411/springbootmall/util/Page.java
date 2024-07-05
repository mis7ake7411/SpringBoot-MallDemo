package com.mis7ake7411.springbootmall.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
  private Integer total;
  private Integer limit;
  private Integer offset;
  private List<T> result;
}
