package dev.jesus.calor_en_la_noche.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.jesus.calor_en_la_noche.user.User;

public class SecurityUser implements UserDetails {

  private User user;

  public SecurityUser(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
  }

}
