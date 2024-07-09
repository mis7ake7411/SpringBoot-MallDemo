package com.mis7ake7411.springbootmall.controller;

import com.mis7ake7411.springbootmall.dto.CreateOrderDto;
import com.mis7ake7411.springbootmall.dto.OrderQueryParams;
import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.service.OrderService;
import com.mis7ake7411.springbootmall.util.Page;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping("/users/{userId}/orders")
  public ResponseEntity<Page<Order>> findAllOrders(@PathVariable Integer userId,
                                                   @RequestParam(defaultValue = "10") @Min(0) @Max(1000) Integer limit,
                                                   @RequestParam(defaultValue = "0") @Min(0) Integer offset) {
    OrderQueryParams orderQueryParams = OrderQueryParams.builder()
                                                        .userId(userId)
                                                        .limit(limit)
                                                        .offset(offset)
                                                        .build();
    List<Order> orderList = orderService.getOrders(orderQueryParams);

    Integer count = orderService.countOrder(orderQueryParams);

    Page<Order> page = new Page<>();
    page.setLimit(limit);
    page.setOffset(offset);
    page.setTotal(count);
    page.setResult(orderList);

    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  @PostMapping("/users/{userId}/orders")
  public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                           @RequestBody @Valid CreateOrderDto createOrderDto) {

    Integer orderId = orderService.createOrder(userId, createOrderDto);
    Order order = orderService.getOrderById(orderId);

    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(order);
  }
}
