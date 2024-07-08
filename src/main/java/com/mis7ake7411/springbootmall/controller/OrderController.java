package com.mis7ake7411.springbootmall.controller;

import com.mis7ake7411.springbootmall.dto.CreateOrderDto;
import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.service.OrderService;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping("/users/{userId}/orders")
  public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                           @RequestBody @Valid CreateOrderDto createOrderDto) {

    Integer orderId = orderService.createOrder(userId, createOrderDto);
    Order order = orderService.getOrderById(orderId);

    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(order);
  }
}
