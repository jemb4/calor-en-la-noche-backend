package dev.jesus.calor_en_la_noche.pdf.dtos;

import java.time.LocalDate;

public record PdfResponse(
    Integer pdfId,
    String name,
    int age,
    LocalDate uploadDay) {

}
