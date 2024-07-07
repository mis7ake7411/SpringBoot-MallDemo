package com.mis7ake7411.springbootmall.dao.impl;

import com.mis7ake7411.springbootmall.dao.UserDao;
import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;
import com.mis7ake7411.springbootmall.rowmapper.UserRowMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
  Date now = new Date();

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public Integer createUser(UserRegisterDto userRegisterDto) {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO user(email, password, created_date, last_modified_date)"
        + " VALUES (:email, :password, :createdDate, :lastModifiedDate)"
    );

    Map<String, Object> params = new HashMap<>();
    params.put("email", userRegisterDto.getEmail());
    params.put("password", userRegisterDto.getPassword());
    params.put("createdDate", now);
    params.put("lastModifiedDate", now);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(params), keyHolder);

    return keyHolder.getKey()
                    .intValue();
  }

  @Override
  public User getUserById(Integer id) {
    StringBuilder sql = new StringBuilder(
        "SELECT user_id, email, password, created_date, last_modified_date"
        + " FROM user WHERE user_id = :id"
    );
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);

    List<User> list = namedParameterJdbcTemplate.query(sql.toString(), params, new UserRowMapper());

    return list.size() > 0
        ? list.get(0) : null;
  }
}
