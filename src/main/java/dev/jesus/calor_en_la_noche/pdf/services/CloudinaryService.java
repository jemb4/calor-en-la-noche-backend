package dev.jesus.calor_en_la_noche.pdf.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
  private final Cloudinary cloudinary;

  public String uploadFile(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("El archivo está vacío.");
    }

    Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
        ObjectUtils.asMap("resource_type", "auto"));

    return (String) uploadResult.get("secure_url");
  }
}
