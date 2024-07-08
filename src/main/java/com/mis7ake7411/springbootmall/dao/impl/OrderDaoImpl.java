package com.mis7ake7411.springbootmall.dao.impl;

import com.mis7ake7411.springbootmall.dao.OrderDao;
import com.mis7ake7411.springbootmall.model.OrderItem;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl implements OrderDao {
  Date now = new Date();

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public Integer createOrder(Integer userId, Integer totalAmount) {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) " +
        "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)"
    );

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("totalAmount", totalAmount);
    params.put("createdDate", now);
    params.put("lastModifiedDate", now);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(params), keyHolder);

    return keyHolder.getKey()
                    .intValue();
  }

  @Override
  public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO order_item (order_id, product_id, quantity, amount) " +
        "VALUES (:orderId, :productId, :quantity, :amount)"
    );

    MapSqlParameterSource[] params = new MapSqlParameterSource[orderItemList.size()];
    for (int i = 0; i < orderItemList.size(); i++) {
      OrderItem orderItem = orderItemList.get(i);
      params[i] = new MapSqlParameterSource();
      params[i].addValue("orderId", orderId);
      params[i].addValue("productId",orderItem.getProduct().getId());
      params[i].addValue("quantity", orderItem.getQuantity());
      params[i].addValue("amount", orderItem.getAmount());
    }

    namedParameterJdbcTemplate.batchUpdate(sql.toString(), params);
  }

}
