package dev.jesus.calor_en_la_noche.pdf.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfRequest;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfResponse;
import dev.jesus.calor_en_la_noche.user.User;

@Component
public class PdfMapper {

  public Pdf toEntity(PdfRequest dto, String url, User user) {
    return Pdf.builder()
        .name(dto.name())
        .age(dto.age())
        .urlPdf(url)
        .uploadDay(LocalDate.now())
        .manager(user)
        .build();
  }

  public PdfResponse toResponse(Pdf pdf) {
    return PdfResponse.builder()
        .pdfId(pdf.getPdfId())
        .name(pdf.getName())
        .urlPdf(pdf.getUrlPdf())
        .age(pdf.getAge())
        .uploadDay(pdf.getUploadDay())
        .managerEmail(pdf.getManager().getEmail())
        .build();
  }
}