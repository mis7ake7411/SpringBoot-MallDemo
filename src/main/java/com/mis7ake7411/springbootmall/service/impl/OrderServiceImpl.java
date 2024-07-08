package com.mis7ake7411.springbootmall.service.impl;

import com.mis7ake7411.springbootmall.dao.OrderDao;
import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.dao.UserDao;
import com.mis7ake7411.springbootmall.dto.BuyItem;
import com.mis7ake7411.springbootmall.dto.CreateOrderDto;
import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.model.OrderItem;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.model.User;
import com.mis7ake7411.springbootmall.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private UserDao userDao;

  @Transactional
  @Override
  public Integer createOrder(Integer userId, CreateOrderDto createOrderDto) {
    // checked user account
    User user = userDao.getUserById(userId);
    if (user == null) {
      log.warn("userId : {} is null", userId);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    int totalAmount = 0;
    List<OrderItem> orderItemList = new ArrayList<>();

    for (BuyItem buyItem: createOrderDto.getBuyItemList()){
      Product product = productDao.getProductById(buyItem.getProductId());

      if (product == null) {
        log.warn("productId: {} is null", buyItem.getProductId());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      } else if (product.getStock() < buyItem.getQuantity()) {
        log.warn("product: {} is not enough, stock: {}, quantity: {}",
            buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }

      productDao.updateStock(product.getId(), product.getStock() - buyItem.getQuantity());

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
    order.setOrderItemList(orderItemList);

    return order;
  }
}
