package com.mis7ake7411.springbootmall.rowmapper;

import com.mis7ake7411.springbootmall.model.Order;
import com.mis7ake7411.springbootmall.model.OrderItem;
import com.mis7ake7411.springbootmall.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
  @Override
  public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
    return OrderItem.builder()
        .id(resultSet.getInt("order_item_id"))
        .orderId(resultSet.getInt("order_id"))
        .productId(resultSet.getInt("product_id"))
        .quantity(resultSet.getInt("quantity"))
        .amount(resultSet.getInt("amount"))
        .productName(resultSet.getString("product_name"))
        .imageUrl(resultSet.getString("image_url"))
        .build();
  }
}
