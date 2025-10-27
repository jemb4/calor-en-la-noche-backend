package dev.jesus.calor_en_la_noche.pdf.services;

import dev.jesus.calor_en_la_noche.pdf.Pdf;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfRequest;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfResponse;
import dev.jesus.calor_en_la_noche.pdf.mappers.PdfMapper;
import dev.jesus.calor_en_la_noche.pdf.repository.PdfRepository;
import dev.jesus.calor_en_la_noche.user.User;
import dev.jesus.calor_en_la_noche.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class PdfServiceTest {

  private PdfRepository pdfRepository;
  private CloudinaryService cloudinaryService;
  private UserRepository userRepository;
  private PdfMapper pdfMapper;
  private PdfService pdfService;
  private Authentication authentication;
  private SecurityContext securityContext;

  @BeforeEach
  void setUp() {
    pdfRepository = mock(PdfRepository.class);
    cloudinaryService = mock(CloudinaryService.class);
    userRepository = mock(UserRepository.class);
    pdfMapper = mock(PdfMapper.class);
    pdfService = new PdfService(pdfRepository, cloudinaryService, userRepository, pdfMapper);

    authentication = mock(Authentication.class);
    securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
  }

  @Test
  void uploadPdfShouldUploadAndReturnResponse() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    PdfRequest request = new PdfRequest("test", 2025);
    User user = new User();
    user.setEmail("test@example.com");

    Pdf pdf = Pdf.builder()
        .pdfId(1)
        .name(request.name())
        .urlPdf("http://cloudinary/pdf.pdf")
        .age(request.age())
        .uploadDay(LocalDate.now())
        .manager(user)
        .build();

    PdfResponse response = PdfResponse.builder()
        .pdfId(1)
        .name(request.name())
        .urlPdf("http://cloudinary/pdf.pdf")
        .age(request.age())
        .uploadDay(LocalDate.now())
        .managerEmail(user.getEmail())
        .build();

    when(file.getContentType()).thenReturn("application/pdf");
    when(file.getOriginalFilename()).thenReturn("test.pdf");
    when(authentication.getName()).thenReturn(user.getEmail());
    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    when(cloudinaryService.uploadFile(file)).thenReturn("http://cloudinary/pdf.pdf");
    when(pdfMapper.toEntity(request, "http://cloudinary/pdf.pdf", user)).thenReturn(pdf);
    when(pdfRepository.save(pdf)).thenReturn(pdf);
    when(pdfMapper.toResponse(pdf)).thenReturn(response);

    PdfResponse result = pdfService.uploadPdf(file, request);

    assertThat(result).isEqualTo(response);
    verify(cloudinaryService).uploadFile(file);
    verify(pdfRepository).save(pdf);
  }

  @Test
  void uploadPdfShouldThrowExceptionForInvalidFile() {
    MultipartFile file = mock(MultipartFile.class);
    PdfRequest request = new PdfRequest("test", 2025);

    when(file.getContentType()).thenReturn("image/png");
    when(file.getOriginalFilename()).thenReturn("file.png");

    assertThatThrownBy(() -> pdfService.uploadPdf(file, request))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Only valid PDF files are allowed.");
  }

  @Test
  void getMyPdfsShouldReturnUserPdfs() {
    User user = new User();
    user.setEmail("test@example.com");
    Pdf pdf = Pdf.builder().pdfId(1).manager(user).build();

    PdfResponse response = PdfResponse.builder()
        .pdfId(1)
        .name("test")
        .urlPdf("url")
        .age(2025)
        .uploadDay(LocalDate.now())
        .managerEmail(user.getEmail())
        .build();

    when(authentication.getName()).thenReturn(user.getEmail());
    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    when(pdfRepository.findByManager(user)).thenReturn(List.of(pdf));
    when(pdfMapper.toResponse(pdf)).thenReturn(response);

    List<PdfResponse> result = pdfService.getMyPdfs();

    assertThat(result).containsExactly(response);
    verify(pdfRepository).findByManager(user);
  }

  @Test
  void getAllPdfsShouldReturnAllResponses() {
    Pdf pdf = Pdf.builder().pdfId(1).build();

    PdfResponse response = PdfResponse.builder()
        .pdfId(1)
        .name("test")
        .urlPdf("url")
        .age(2025)
        .uploadDay(LocalDate.now())
        .managerEmail("admin@example.com")
        .build();

    when(pdfRepository.findAll()).thenReturn(List.of(pdf));
    when(pdfMapper.toResponse(pdf)).thenReturn(response);

    List<PdfResponse> result = pdfService.getAllPdfs();

    assertThat(result).containsExactly(response);
    verify(pdfRepository).findAll();
  }

  @Test
  void deletePdfShouldDeleteWhenUserIsOwner() {
    User user = new User();
    user.setEmail("test@example.com");

    Pdf pdf = Pdf.builder()
        .pdfId(1)
        .name("report.pdf")
        .urlPdf("url")
        .age(2025)
        .uploadDay(LocalDate.now())
        .manager(user)
        .build();

    when(authentication.getName()).thenReturn(user.getEmail());
    when(pdfRepository.findById(1)).thenReturn(Optional.of(pdf));
    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

    pdfService.deletePdf(1);

    verify(pdfRepository).delete(pdf);
  }

  @Test
  void deletePdfShouldThrowWhenUserNotOwner() {
    User owner = new User();
    owner.setEmail("owner@example.com");
    User another = new User();
    another.setEmail("other@example.com");

    Pdf pdf = Pdf.builder().pdfId(1).manager(owner).build();

    when(authentication.getName()).thenReturn(another.getEmail());
    when(pdfRepository.findById(1)).thenReturn(Optional.of(pdf));
    when(userRepository.findByEmail(another.getEmail())).thenReturn(Optional.of(another));

    assertThatThrownBy(() -> pdfService.deletePdf(1))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("No tienes permiso para eliminar este PDF.");
  }

  @Test
  void deletePdfShouldThrowWhenPdfNotFound() {
    when(pdfRepository.findById(1)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> pdfService.deletePdf(1))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("PDF no encontrado.");
  }
}
