package dev.jesus.calor_en_la_noche.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.jesus.calor_en_la_noche.role.Role;

public class UserTest {

  @Test
  void shouldCreateUserWithBuilder() {
    User user = User.builder()
        .email("email@example.com")
        .passwordHash("hashedPass")
        .build();

    assertThat(user.getEmail()).isEqualTo("email@example.com");
    assertThat(user.getPasswordHash()).isEqualTo("hashedPass");
  }

  @Test
  void shouldAllowSettingAndGettingRoles() {
    Role role = new Role();
    role.setName("ADMIN");

    User user = new User();
    user.setRoles(Set.of(role));

    assertThat(user.getRoles()).hasSize(1);
    assertThat(user.getRoles().iterator().next().getName()).isEqualTo("ADMIN");
  }

  @Test
  void equalsShouldReturnTrueForSameId() {
    User user1 = User.builder().id(3L).build();
    User user2 = User.builder().id(3L).build();

    assertThat(user1).isEqualTo(user2);
    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
  }

  @Test
  void equalsShouldReturnFalseForDifferentEmail() {
    User user1 = User.builder().email("email@example.com").build();
    User user2 = User.builder().email("email2@example.com").build();

    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void equalsShouldReturnFalseWhenemailIsNull() {
    User user1 = User.builder().email(null).build();
    User user2 = User.builder().email("email@example.com").build();

    assertThat(user1).isNotEqualTo(user2);
  }

}
