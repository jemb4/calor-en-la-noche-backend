package dev.jesus.calor_en_la_noche.pdf.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.pdf.repository.PdfRepository;
import dev.jesus.calor_en_la_noche.user.User;

@Service
public class PdfService {

  private final Cloudinary cloudinary;
  private final PdfRepository pdfRepository;

  public PdfService(Cloudinary cloudinary, PdfRepository pdfRepository) {
    this.cloudinary = cloudinary;
    this.pdfRepository = pdfRepository;
  }

  public Pdf uploadPdf(MultipartFile file, String name, int age, User manager) throws IOException {
    Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
        ObjectUtils.asMap("resource_type", "auto")); // "auto" detecta PDFs automáticamente

    String urlPdf = (String) uploadResult.get("secure_url");

    Pdf pdf = Pdf.builder()
        .name(name)
        .age(age)
        .uploadDay(java.time.LocalDate.now())
        .manager(manager)
        .urlPdf(urlPdf)
        .build();

    return pdfRepository.save(pdf);
  }
}
