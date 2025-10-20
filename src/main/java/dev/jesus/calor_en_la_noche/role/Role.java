package dev.jesus.calor_en_la_noche.role;

import java.util.Set;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.jesus.calor_en_la_noche.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@RequiredArgsConstructor
@Getter
@Setter
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_role")
  private Long id;

  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
}
