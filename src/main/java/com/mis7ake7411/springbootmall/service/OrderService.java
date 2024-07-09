package com.mis7ake7411.springbootmall.service;

import com.mis7ake7411.springbootmall.dto.CreateOrderDto;
import com.mis7ake7411.springbootmall.dto.OrderQueryParams;
import com.mis7ake7411.springbootmall.model.Order;
import java.util.List;

public interface OrderService {
  Integer createOrder(Integer userId, CreateOrderDto createOrderDto);
  Order getOrderById(Integer orderId);
  List<Order> getOrders(OrderQueryParams orderQueryParams);
  Integer countOrder(OrderQueryParams orderQueryParams);
}
