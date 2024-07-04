package com.mis7ake7411.springbootmall.service;

import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer id);
    Integer createProduct(ProductDto productDto);
}
