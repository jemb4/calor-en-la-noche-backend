package dev.jesus.calor_en_la_noche.pdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jesus.calor_en_la_noche.pdf.Pdf;

public interface Repository extends JpaRepository<Pdf, Long> {

}
