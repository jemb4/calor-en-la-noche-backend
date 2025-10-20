package dev.jesus.calor_en_la_noche.user.services;

import org.springframework.stereotype.Service;

import dev.jesus.calor_en_la_noche.user.User;
import dev.jesus.calor_en_la_noche.user.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
