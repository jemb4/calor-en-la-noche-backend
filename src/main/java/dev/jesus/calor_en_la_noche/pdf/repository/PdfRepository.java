package dev.jesus.calor_en_la_noche.pdf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.user.User;

public interface PdfRepository extends JpaRepository<Pdf, Integer> {
  List<Pdf> findByManager(User user);
}
