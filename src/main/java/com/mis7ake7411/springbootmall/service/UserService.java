package com.mis7ake7411.springbootmall.service;

import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;

public interface UserService {
  Integer register(UserRegisterDto userRegisterDto);
  User getUserById(Integer id);
}
