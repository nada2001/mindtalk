package com.nada.mindtalk.exceptions;

public class UserEmailAlreadyExistException extends RuntimeException {

  public UserEmailAlreadyExistException(String email) {
    super("User with email " + email + " already exists!");
  }
}
