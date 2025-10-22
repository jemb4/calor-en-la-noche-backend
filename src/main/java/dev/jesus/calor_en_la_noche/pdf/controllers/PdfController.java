package dev.jesus.calor_en_la_noche.pdf.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.jesus.calor_en_la_noche.pdf.dtos.PdfRequest;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfResponse;
import dev.jesus.calor_en_la_noche.pdf.services.PdfService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api-endpoint}/pdf")
@RequiredArgsConstructor
public class PdfController {

  private final PdfService pdfService;

  @PostMapping("/upload")
  public ResponseEntity<PdfResponse> uploadPdf(
      @RequestPart("file") MultipartFile file,
      @RequestPart("data") PdfRequest dto) {
    try {
      PdfResponse response = pdfService.uploadPdf(file, dto);
      return ResponseEntity.ok(response);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().body(null);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @GetMapping("/mine")
  public ResponseEntity<List<PdfResponse>> getMyPdfs() {
    return ResponseEntity.ok(pdfService.getMyPdfs());
  }

  @GetMapping("")
  public ResponseEntity<List<PdfResponse>> getAllPdfs() {
    return ResponseEntity.ok(pdfService.getAllPdfs());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePdf(@PathVariable Integer id) {
    try {
      pdfService.deletePdf(id);
      return ResponseEntity.ok("PDF eliminado correctamente.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(403).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Error al eliminar el PDF.");
    }
  }
}
