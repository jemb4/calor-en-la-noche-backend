package dev.jesus.calor_en_la_noche.pdf;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.jesus.calor_en_la_noche.user.User;

public class PdfTest {

  private User manager;

  @BeforeEach
  void setUp() {
    manager = User.builder()
        .id(1L)
        .email("manager@manager.com")
        .build();
  }

  @Test
  void shouldCreatePdfWithSetters() {
    Pdf pdf = new Pdf();
    pdf.setPdfId(2);
    pdf.setName("report.pdf");
    pdf.setUrlPdf("https://example.com/report.pdf");
    pdf.setAge(2024);
    pdf.setUploadDay(LocalDate.of(2024, 10, 20));
    pdf.setManager(manager);

    assertThat(pdf.getPdfId()).isEqualTo(2);
    assertThat(pdf.getName()).isEqualTo("report.pdf");
    assertThat(pdf.getUrlPdf()).isEqualTo("https://example.com/report.pdf");
    assertThat(pdf.getAge()).isEqualTo(2024);
    assertThat(pdf.getUploadDay()).isEqualTo(LocalDate.of(2024, 10, 20));
    assertThat(pdf.getManager()).isEqualTo(manager);
    assertThat(pdf.getManager().getEmail()).isEqualTo("manager@manager.com");
  }

  @Test
  void shouldCreatePdfWithBuilder() {
    Pdf pdf = Pdf.builder()
        .pdfId(3)
        .name("summary.pdf")
        .urlPdf("https://example.com/summary.pdf")
        .age(2023)
        .uploadDay(LocalDate.of(2023, 5, 15))
        .manager(manager)
        .build();

    assertThat(pdf.getPdfId()).isEqualTo(3);
    assertThat(pdf.getName()).isEqualTo("summary.pdf");
    assertThat(pdf.getUrlPdf()).isEqualTo("https://example.com/summary.pdf");
    assertThat(pdf.getAge()).isEqualTo(2023);
    assertThat(pdf.getUploadDay()).isEqualTo(LocalDate.of(2023, 5, 15));
    assertThat(pdf.getManager().getEmail()).isEqualTo("manager@manager.com");
  }

  @Test
  void equalsShouldReturnFalseForDifferentIds() {
    Pdf pdf1 = Pdf.builder().pdfId(1).name("file1.pdf").build();
    Pdf pdf2 = Pdf.builder().pdfId(2).name("file1.pdf").build();

    assertThat(pdf1).isNotEqualTo(pdf2);
  }

  @Test
  void equalsShouldReturnFalseWhenIdIsNull() {
    Pdf pdf1 = Pdf.builder().pdfId(null).name("file1.pdf").build();
    Pdf pdf2 = Pdf.builder().pdfId(1).name("file1.pdf").build();

    assertThat(pdf1).isNotEqualTo(pdf2);
  }
}
