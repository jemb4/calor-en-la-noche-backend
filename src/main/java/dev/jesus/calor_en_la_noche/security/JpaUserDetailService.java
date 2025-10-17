package dev.jesus.calor_en_la_noche.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.jesus.calor_en_la_noche.user.User;
import dev.jesus.calor_en_la_noche.user.repository.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public JpaUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // User user = userRepository.findByEmail(email)
    // .map(TODO)
    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
  }

}
