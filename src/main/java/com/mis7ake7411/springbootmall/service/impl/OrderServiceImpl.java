package com.mis7ake7411.springbootmall.service.impl;

import com.mis7ake7411.springbootmall.dao.OrderDao;
import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.dto.BuyItem;
import com.mis7ake7411.springbootmall.dto.CreateOrderDto;
import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.model.OrderItem;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private ProductDao productDao;

  @Transactional
  @Override
  public Integer createOrder(Integer userId, CreateOrderDto createOrderDto) {
    int totalAmount = 0;
    List<OrderItem> orderItemList = new ArrayList<>();

    for (BuyItem buyItem: createOrderDto.getBuyItemList()){
      Product product = productDao.getProductById(buyItem.getProductId());
      // calculate total amount
      int amount = buyItem.getQuantity() * product.getPrice();
      totalAmount += amount;
      // convert BuyItem to OrderItem
      OrderItem orderItem = OrderItem.builder()
                                     .productId(product.getId())
                                     .amount(amount)
                                     .quantity(buyItem.getQuantity())
                                     .build();

      orderItemList.add(orderItem);
    }
    // create order
    Integer orderId = orderDao.createOrder(userId, totalAmount);

    orderDao.createOrderItems(orderId, orderItemList);

    return orderId;
  }

  @Override
  public Order getOrderById(Integer orderId) {
    Order order = orderDao.getOrderById(orderId);
    List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
    System.out.println("================> "+orderItemList);
    order.setOrderItemList(orderItemList);

    return order;
  }
}
