package com.mis7ake7411.springbootmall.rowmapper;

import com.mis7ake7411.springbootmall.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    return User.builder()
                .id(resultSet.getInt("user_id"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .createdDate(resultSet.getTimestamp("created_date"))
                .lastModifiedDate(resultSet.getTimestamp("last_modified_date"))
                .build();
  }
}
