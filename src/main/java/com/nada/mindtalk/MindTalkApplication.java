package com.nada.mindtalk;

import com.nada.mindtalk.enums.Etype;
import com.nada.mindtalk.models.User;
import com.nada.mindtalk.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class MindTalkApplication implements CommandLineRunner {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(MindTalkApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    userRepository.findByEmail("nada@gmail.com").ifPresentOrElse(user -> {
      log.info("DEFAULT USER[{}] ALREADY EXISTS","nada@gmail.com");
    }, () -> {
      User user = new User();
      user.setEmail("nada@gmail.com");
      user.setNames("NADA");
      user.setPassword(passwordEncoder.encode("123login"));
      user.setType(Etype.CLIENT);
      userRepository.save(user);
      log.info("DEFAULT USER[{}] CREATED SUCCESSFULLY","nada@gmail.com");
    });
    userRepository.findByEmail("teta@gmail.com").ifPresentOrElse(user -> {
      log.info("DEFAULT USER[{}] ALREADY EXISTS","teta@gmail.com");
    }, () -> {
      User user = new User();
      user.setEmail("teta@gmail.com");
      user.setNames("TETA");
      user.setPassword(passwordEncoder.encode("123login"));
      user.setType(Etype.CLIENT);
      userRepository.save(user);
      log.info("DEFAULT USER[{}] CREATED SUCCESSFULLY","teta@gmail.com");
    });
  }
}
