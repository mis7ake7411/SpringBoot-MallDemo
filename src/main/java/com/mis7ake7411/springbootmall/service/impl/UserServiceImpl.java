package com.mis7ake7411.springbootmall.service.impl;

import com.mis7ake7411.springbootmall.dao.UserDao;
import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;
import com.mis7ake7411.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;

  @Override
  public Integer register(UserRegisterDto userRegisterDto) {
    return userDao.createUser(userRegisterDto);
  }

  @Override
  public User getUserById(Integer id) {
    return userDao.getUserById(id);
  }
}
