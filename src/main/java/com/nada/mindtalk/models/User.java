package com.nada.mindtalk.models;


import com.nada.mindtalk.enums.Etype;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String names;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Etype type = Etype.CLIENT;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(type.name()));
  }

  @Override
  public String getUsername() {
    return email;
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
