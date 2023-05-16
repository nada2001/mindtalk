package com.nada.mindtalk.controllers;

import com.nada.mindtalk.dtos.CreateUserDto;
import com.nada.mindtalk.exceptions.UserEmailAlreadyExistException;
import com.nada.mindtalk.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final UserService userService;

  public MainController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/login")
  String viewLoginPage(Model model) {
    return "pages/login";
  }

  @GetMapping("/")
  String viewHome(Model model) {
    return "pages/index";
  }

  @PostMapping("/register")
  String register(Model model, @ModelAttribute("user") @Valid CreateUserDto createUserDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "pages/registration";
    }
    try {
      userService.createUser(createUserDto);
    } catch (UserEmailAlreadyExistException e) {
      model.addAttribute("message", e.getMessage());
      model.addAttribute("user", createUserDto);
      return "pages/registration";
    }
    return "pages/login";
  }

  @GetMapping("/register")
  String viewRegisterPage(Model model) {
    model.addAttribute("user", new CreateUserDto());
    return "pages/registration";
  }
}
