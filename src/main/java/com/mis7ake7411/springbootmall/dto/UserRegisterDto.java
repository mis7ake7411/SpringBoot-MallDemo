package com.mis7ake7411.springbootmall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;
}
