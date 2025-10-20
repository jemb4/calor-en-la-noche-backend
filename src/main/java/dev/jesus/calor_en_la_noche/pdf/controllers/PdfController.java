package dev.jesus.calor_en_la_noche.pdf.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.pdf.services.PdfService;
import dev.jesus.calor_en_la_noche.user.User;

@RestController
@RequestMapping("${api-endpoint}/pdf")
public class PdfController {
  private final PdfService pdfService;

  public PdfController(PdfService pdfService) {
    this.pdfService = pdfService;
  }

  @PostMapping("/upload")
  public ResponseEntity<Pdf> uploadPdf(
      @RequestParam("file") MultipartFile file,
      @RequestParam("name") String name,
      @RequestParam("age") int age,
      @RequestParam("userId") Integer userId // Se asume que obtienes el usuario por ID
  ) {
    // Aquí debes recuperar el User de tu repositorio
    User manager = new User(); // reemplaza con la lógica real para obtener al usuario
    manager.setId(userId);

    try {
      Pdf pdf = pdfService.uploadPdf(file, name, age, manager);
      return ResponseEntity.ok(pdf);
    } catch (Exception e) {
      return ResponseEntity.status(500).build();
    }
  }
}
