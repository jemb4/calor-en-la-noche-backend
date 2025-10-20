package dev.jesus.calor_en_la_noche.pdf.dtos;

import java.time.LocalDate;

import dev.jesus.calor_en_la_noche.user.User;

public record PdfRequest(
    String name,
    int age,
    LocalDate uploadDay,
    User user) {

}
