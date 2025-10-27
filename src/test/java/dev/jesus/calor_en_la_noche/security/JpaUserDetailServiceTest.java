package dev.jesus.calor_en_la_noche.security;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.jesus.calor_en_la_noche.user.User;
import dev.jesus.calor_en_la_noche.user.repository.UserRepository;

public class JpaUserDetailServiceTest {

  private UserRepository userRepository;
  private JpaUserDetailService jpaUserDetailService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    jpaUserDetailService = new JpaUserDetailService(userRepository);
  }

  @Test
  void loadUserByUsernameShouldReturnSecurityUserWhenUserExists() {
    String email = "test@example.com";
    User user = new User();

    when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
    UserDetails result = jpaUserDetailService.loadUserByUsername(email);

    assertThat(result).isInstanceOf(SecurityUser.class);
    verify(userRepository).findByEmail(email);
  }

  @Test
  void loadUserByUsernameShouldThrowExceptionWhenUserNotFound() {
    String email = "notfound@example.com";
    when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> jpaUserDetailService.loadUserByUsername(email))
        .isInstanceOf(UsernameNotFoundException.class)
        .hasMessage("User with email " + email + " not found");

    verify(userRepository).findByEmail(email);
  }
}
