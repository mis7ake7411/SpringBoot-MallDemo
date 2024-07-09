package com.mis7ake7411.springbootmall.dao;

import com.mis7ake7411.springbootmall.dto.OrderQueryParams;
import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.model.OrderItem;
import java.util.List;

public interface OrderDao {
  Integer createOrder(Integer userId, Integer totalAmount);
  void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
  Order getOrderById(Integer orderId);
  List<OrderItem> getOrderItemByOrderId(Integer orderId);
  List<Order> getOrders(OrderQueryParams orderQueryParams);
  Integer countOrder(OrderQueryParams orderQueryParams);
}
