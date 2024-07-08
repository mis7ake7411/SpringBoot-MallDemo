package com.mis7ake7411.springbootmall.dao;

import com.mis7ake7411.springbootmall.model.OrderItem;
import java.util.List;

public interface OrderDao {
  Integer createOrder(Integer userId, Integer totalAmount);
  void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
