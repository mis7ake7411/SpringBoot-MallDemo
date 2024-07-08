package com.mis7ake7411.springbootmall.rowmapper;

import com.mis7ake7411.springbootmall.model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {
  @Override
  public Order mapRow(ResultSet resultSet, int i) throws SQLException {
    return Order.builder()
                .id(resultSet.getInt("order_id"))
                .userId(resultSet.getInt("user_id"))
                .totalAmount(resultSet.getInt("total_amount"))
                .createdDate(resultSet.getTimestamp("created_date"))
                .lastModifiedDate(resultSet.getTimestamp("last_modified_date"))
                .build();
  }
}
