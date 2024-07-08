package com.mis7ake7411.springbootmall.service;

import com.mis7ake7411.springbootmall.dto.CreateOrderDto;

public interface OrderService {
  Integer createOrder(Integer userId, CreateOrderDto createOrderDto);
}
