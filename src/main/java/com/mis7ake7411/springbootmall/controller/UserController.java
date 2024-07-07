package com.mis7ake7411.springbootmall.controller;

import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;
import com.mis7ake7411.springbootmall.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/users/register")
  public ResponseEntity<User> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
    Integer id = userService.register(userRegisterDto);
    User user = userService.getUserById(id);

    return ResponseEntity.ok(user);
  }

}
