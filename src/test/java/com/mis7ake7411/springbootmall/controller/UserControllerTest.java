package com.mis7ake7411.springbootmall.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mis7ake7411.springbootmall.dao.UserDao;
import com.mis7ake7411.springbootmall.dto.UserLoginDto;
import com.mis7ake7411.springbootmall.dto.UserRegisterDto;
import com.mis7ake7411.springbootmall.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserDao userDao;

  private ObjectMapper objectMapper = new ObjectMapper();

  // 註冊新帳號
  @Test
  public void register_success() throws Exception {
    UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                                                     .email("test1@gmail.com")
                                                     .password("123")
                                                     .build();

    String json = objectMapper.writeValueAsString(userRegisterDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(201))
           .andExpect(jsonPath("$.id", notNullValue()))
           .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
           .andExpect(jsonPath("$.createdDate", notNullValue()))
           .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));

    // 檢查資料庫中的密碼不為明碼
    User user = userDao.getUserByEmail(userRegisterDto.getEmail());
    assertNotEquals(userRegisterDto.getPassword(), user.getPassword());
  }

  @Test
  public void register_invalidEmailFormat() throws Exception {
    UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                                                     .email("3gd8e7q34l9")
                                                     .password("123")
                                                     .build();

    String json = objectMapper.writeValueAsString(userRegisterDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(400));
  }

  @Test
  public void register_emailAlreadyExist() throws Exception {
    // 先註冊一個帳號
    UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                                                     .email("test2@gmail.com")
                                                     .password("123")
                                                     .build();

    String json = objectMapper.writeValueAsString(userRegisterDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(201));

    // 再次使用同個 email 註冊
    mockMvc.perform(requestBuilder)
           .andExpect(status().is(400));
  }

  // 登入
  @Test
  public void login_success() throws Exception {
    // 先註冊新帳號
    UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                                                     .email("test3@gmail.com")
                                                     .password("123")
                                                     .build();

    register(userRegisterDto);

    // 再測試登入功能
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail(userLoginDto.getEmail());
    userLoginDto.setPassword(userLoginDto.getPassword());

    String json = objectMapper.writeValueAsString(userRegisterDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(200))
           .andExpect(jsonPath("$.id", notNullValue()))
           .andExpect(jsonPath("$.email", equalTo(userRegisterDto.getEmail())))
           .andExpect(jsonPath("$.createdDate", notNullValue()))
           .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
  }

  @Test
  public void login_wrongPassword() throws Exception {
    // 先註冊新帳號
    UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                                                     .email("test4@gmail.com")
                                                     .password("123")
                                                     .build();

    register(userRegisterDto);

    // 測試密碼輸入錯誤的情況
    UserLoginDto userLoginDto = UserLoginDto.builder()
                                            .email(userRegisterDto.getEmail())
                                            .password("unknown")
                                            .build();

    String json = objectMapper.writeValueAsString(userLoginDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(400));
  }

  @Test
  public void login_invalidEmailFormat() throws Exception {
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail("hkbudsr324");
    userLoginDto.setPassword("123");

    String json = objectMapper.writeValueAsString(userLoginDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(400));
  }

  @Test
  public void login_emailNotExist() throws Exception {
    UserLoginDto userLoginDto = UserLoginDto.builder()
                                            .email("unknown@gmail.com")
                                            .password("123")
                                            .build();

    String json = objectMapper.writeValueAsString(userLoginDto);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(400));
  }

  private void register(UserRegisterDto userRegisterRequest) throws Exception {
    String json = objectMapper.writeValueAsString(userRegisterRequest);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(json);

    mockMvc.perform(requestBuilder)
           .andExpect(status().is(201));
  }
}