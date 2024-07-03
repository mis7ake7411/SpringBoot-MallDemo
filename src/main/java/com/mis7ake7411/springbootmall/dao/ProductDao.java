package com.mis7ake7411.springbootmall.dao;

import com.mis7ake7411.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);
}
