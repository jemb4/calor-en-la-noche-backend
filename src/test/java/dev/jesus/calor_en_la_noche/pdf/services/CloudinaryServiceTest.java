package dev.jesus.calor_en_la_noche.pdf.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CloudinaryServiceTest {

  private Cloudinary cloudinary;
  private Uploader uploader;
  private CloudinaryService cloudinaryService;

  @BeforeEach
  void setUp() {
    cloudinary = mock(Cloudinary.class);
    uploader = mock(Uploader.class);
    cloudinaryService = new CloudinaryService(cloudinary);
  }

  @Test
  void uploadFileShouldReturnSecureUrlWhenUploadSucceeds() throws Exception {
    MultipartFile file = mock(MultipartFile.class);
    when(file.isEmpty()).thenReturn(false);
    when(file.getBytes()).thenReturn("dummy-content".getBytes());

    when(cloudinary.uploader()).thenReturn(uploader);
    when(uploader.upload(any(byte[].class), anyMap()))
        .thenReturn(Map.of("secure_url", "https://example.com/test.pdf"));

    String result = cloudinaryService.uploadFile(file);

    assertThat(result).isEqualTo("https://example.com/test.pdf");
    verify(file).isEmpty();
    verify(file).getBytes();
    verify(uploader).upload(any(byte[].class), anyMap());
  }

  @Test
  void uploadFileShouldThrowExceptionWhenFileIsEmpty() {
    MultipartFile file = mock(MultipartFile.class);
    when(file.isEmpty()).thenReturn(true);

    assertThatThrownBy(() -> cloudinaryService.uploadFile(file))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("El archivo está vacío.");

    verify(file).isEmpty();
    verifyNoInteractions(cloudinary);
  }

  @Test
  void uploadFileShouldThrowIOExceptionWhenUploaderFails() throws Exception {
    MultipartFile file = mock(MultipartFile.class);
    when(file.isEmpty()).thenReturn(false);
    when(file.getBytes()).thenReturn("dummy".getBytes());

    when(cloudinary.uploader()).thenReturn(uploader);
    when(uploader.upload(any(byte[].class), anyMap())).thenThrow(new IOException("Upload failed"));

    assertThatThrownBy(() -> cloudinaryService.uploadFile(file))
        .isInstanceOf(IOException.class)
        .hasMessage("Upload failed");

    verify(file).isEmpty();
    verify(file).getBytes();
    verify(uploader).upload(any(byte[].class), anyMap());
  }
}
