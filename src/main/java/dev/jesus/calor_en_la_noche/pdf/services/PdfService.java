package dev.jesus.calor_en_la_noche.pdf.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfRequest;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfResponse;
import dev.jesus.calor_en_la_noche.pdf.mappers.PdfMapper;
import dev.jesus.calor_en_la_noche.pdf.repository.PdfRepository;
import dev.jesus.calor_en_la_noche.user.User;
import dev.jesus.calor_en_la_noche.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

  private final PdfRepository pdfRepository;
  private final CloudinaryService cloudinaryService;
  private final UserRepository userRepository;
  private final PdfMapper pdfMapper;

  public PdfResponse uploadPdf(MultipartFile file, PdfRequest dto) throws IOException {
    String contentType = file.getContentType();
    String filename = file.getOriginalFilename();

    if (contentType == null ||
        !contentType.equalsIgnoreCase("application/pdf") ||
        (filename != null && !filename.toLowerCase().endsWith(".pdf"))) {
      throw new IllegalArgumentException("Only valid PDF files are allowed.");
    }

    String url = cloudinaryService.uploadFile(file);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User with email: " + email + " not founded."));

    Pdf pdf = pdfMapper.toEntity(dto, url, user);
    Pdf saved = pdfRepository.save(pdf);

    return pdfMapper.toResponse(saved);
  }

  public List<PdfResponse> getMyPdfs() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User with email: " + email + " not founded."));

    return pdfRepository.findByManager(user).stream()
        .map(pdfMapper::toResponse)
        .collect(Collectors.toList());
  }

  public List<PdfResponse> getAllPdfs() {
    return pdfRepository.findAll().stream()
        .map(pdfMapper::toResponse)
        .collect(Collectors.toList());
  }

  public void deletePdf(Integer pdfId) {
    Pdf pdf = pdfRepository.findById(pdfId)
        .orElseThrow(() -> new RuntimeException("PDF no encontrado."));

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

    if (!pdf.getManager().getEmail().equals(user.getEmail())) {
      throw new RuntimeException("No tienes permiso para eliminar este PDF.");
    }

    pdfRepository.delete(pdf);
  }
}
