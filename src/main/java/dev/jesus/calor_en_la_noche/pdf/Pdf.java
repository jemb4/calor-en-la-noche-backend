package dev.jesus.calor_en_la_noche.pdf;

import java.time.LocalDate;

import dev.jesus.calor_en_la_noche.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pdfs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pdf {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer pdfId;

  private String name;
  private int age;
  private LocalDate uploadDay;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User manager;

}
