package com.nada.mindtalk.services;

import com.nada.mindtalk.dtos.CreateUserDto;
import com.nada.mindtalk.exceptions.UserEmailAlreadyExistException;
import com.nada.mindtalk.models.User;
import com.nada.mindtalk.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final SendGridEmailService sendGridEmailService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  public User createUser(CreateUserDto createUserDto) {
    if (userRepository.existsByEmail(createUserDto.getEmail())) {
      throw new UserEmailAlreadyExistException(createUserDto.getEmail());
    }

    User user = new User();
    user.setNames(createUserDto.getNames());
    user.setEmail(createUserDto.getEmail());
    user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    User savedUser = userRepository.save(user);

    sendGridEmailService.sendText("no-reply@mindtalk.com", savedUser.getEmail(),
        "Welcome to MindTalk", """
            Welcome to Mindtalk, Where Support Unites.
            Discover Strength in Vulnerability
            """);

    return savedUser;
  }
}
