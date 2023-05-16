package com.nada.mindtalk.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateUserDto {

  @NotBlank(message = "Names are required")
  private String names;
  @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$", message = "Invalid email address")
  private String email;
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
      message = "Password must contain at least One uppercase letter, 8 characters"
  )
  private String password;

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
