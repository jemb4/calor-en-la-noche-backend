package dev.jesus.calor_en_la_noche.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.jesus.calor_en_la_noche.role.Role;
import dev.jesus.calor_en_la_noche.user.User;

public class SecurityUser implements UserDetails {

  private User user;

  public SecurityUser(User user) {
    this.user = user;
  }

  @Override
  public String getPassword() {
    return user.getPasswordHash();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for (Role role : user.getRoles()) {
      System.out.println("User role : " + role.getName());
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
      authorities.add(authority);
    }

    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
