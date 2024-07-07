package com.mis7ake7411.springbootmall.service.impl;

import com.mis7ake7411.springbootmall.dao.UserDao;
import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;
import com.mis7ake7411.springbootmall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;

  @Override
  public Integer register(UserRegisterDto userRegisterDto) {
    User user = userDao.getUserByEmail(userRegisterDto.getEmail());
    if (user != null) {
      log.warn("{} has been registered.", userRegisterDto.getEmail());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return userDao.createUser(userRegisterDto);
  }

  @Override
  public User getUserById(Integer id) {
    return userDao.getUserById(id);
  }
}
