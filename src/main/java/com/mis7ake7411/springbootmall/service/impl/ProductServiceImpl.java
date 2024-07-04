package com.mis7ake7411.springbootmall.service.impl;

import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public Integer createProduct(ProductDto productDto) {
        return productDao.createProduct(productDto);
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        productDao.updateProduct(id, productDto);
    }

    @Override
    public void deleteProductById(Integer id) {
        productDao.deleteProductById(id);
    }
}

