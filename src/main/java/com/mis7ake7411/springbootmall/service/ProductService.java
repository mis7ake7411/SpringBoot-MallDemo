package com.mis7ake7411.springbootmall.service;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.dto.ProductQueryParams;
import com.mis7ake7411.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams queryParams);
    Product getProductById(Integer id);
    Integer createProduct(ProductDto productDto);
    void updateProduct(Integer id, ProductDto productDto);
    void deleteProductById(Integer id);
}
