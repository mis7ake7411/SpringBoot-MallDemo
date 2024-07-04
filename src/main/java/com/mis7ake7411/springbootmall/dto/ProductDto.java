package com.mis7ake7411.springbootmall.dto;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import lombok.Data;
import lombok.NonNull;

@Data
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
