package dev.jesus.calor_en_la_noche.pdf.dtos;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PdfResponse(
        Integer pdfId,
        String name,
        String urlPdf,
        int age,
        LocalDate uploadDay,
        String managerEmail) {

}
