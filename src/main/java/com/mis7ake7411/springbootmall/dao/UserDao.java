package com.mis7ake7411.springbootmall.dao;

import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;

public interface UserDao {
  Integer createUser(UserRegisterDto userRegisterDto);
  User getUserById(Integer id);
}
