package com.mis7ake7411.springbootmall.dto;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NonNull
    private String productName;
    @NonNull
    private ProductCategory category;
    @NonNull
    private String imageUrl;
    @NonNull
    private Integer price;
    @NonNull
    private Integer stock;

    private String description;
}
