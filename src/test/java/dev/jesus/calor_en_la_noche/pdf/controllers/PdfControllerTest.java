package dev.jesus.calor_en_la_noche.pdf.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import dev.jesus.calor_en_la_noche.pdf.dtos.PdfRequest;
import dev.jesus.calor_en_la_noche.pdf.dtos.PdfResponse;
import dev.jesus.calor_en_la_noche.pdf.services.PdfService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin1@calor.com", roles = { "ADMIN" })
@ActiveProfiles("test")
class PdfControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PdfService pdfService;

  @Test
  @DisplayName("POST /api/v1/pdf/upload debe subir correctamente un PDF")
  void testUploadPdf() throws Exception {
    // Mock file PDF
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "documento.pdf",
        "application/pdf",
        "contenido-pdf".getBytes(StandardCharsets.UTF_8));

    // Mock JSON para el campo "data"
    String jsonData = """
        {
          "name": "test.pdf",
          "age": 2025
        }
        """;

    MockMultipartFile dataPart = new MockMultipartFile(
        "data", "", "application/json", jsonData.getBytes(StandardCharsets.UTF_8));

    // Mock respuesta esperada del servicio
    PdfResponse mockResponse = PdfResponse.builder()
        .pdfId(1)
        .name("test.pdf")
        .urlPdf("https://res.cloudinary.com/fake.pdf")
        .age(2025)
        .uploadDay(LocalDate.of(2025, 10, 27))
        .managerEmail("admin@calor.com")
        .build();

    when(pdfService.uploadPdf(any(), any(PdfRequest.class))).thenReturn(mockResponse);

    mockMvc.perform(multipart("/api/v1/pdf/upload")
        .file(file)
        .file(dataPart)
        .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("test.pdf"))
        .andExpect(jsonPath("$.urlPdf").value("https://res.cloudinary.com/fake.pdf"))
        .andExpect(jsonPath("$.managerEmail").value("admin@calor.com"));
  }

  @Test
  @DisplayName("GET /api/v1/pdf/mine devuelve lista de PDFs del usuario")
  void testGetMyPdfs() throws Exception {
    PdfResponse pdf1 = PdfResponse.builder()
        .pdfId(1)
        .name("test1.pdf")
        .urlPdf("url1")
        .age(2022)
        .uploadDay(LocalDate.of(2025, 10, 22))
        .managerEmail("admin1@calor.com")
        .build();

    PdfResponse pdf2 = PdfResponse.builder()
        .pdfId(2)
        .name("test2.pdf")
        .urlPdf("url2")
        .age(2023)
        .uploadDay(LocalDate.of(2025, 10, 23))
        .managerEmail("admin2@calor.com")
        .build();

    when(pdfService.getMyPdfs()).thenReturn(List.of(pdf1, pdf2));

    mockMvc.perform(get("/api/v1/pdf/mine"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].name").value("test1.pdf"));
  }

  @Test
  @DisplayName("GET /api/v1/pdf/all devuelve todos los PDFs")
  void testGetAllPdfs() throws Exception {
    PdfResponse pdf = PdfResponse.builder()
        .pdfId(1)
        .name("test.pdf")
        .urlPdf("url")
        .age(2022)
        .uploadDay(LocalDate.of(2025, 10, 22))
        .managerEmail("admin@calor.com")
        .build();

    when(pdfService.getAllPdfs()).thenReturn(List.of(pdf));

    mockMvc.perform(get("/api/v1/pdf/all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1))
        .andExpect(jsonPath("$[0].name").value("test.pdf"));
  }

  @Test
  @DisplayName("DELETE /api/v1/pdf/{id} elimina correctamente un PDF")
  void testDeletePdf() throws Exception {
    doNothing().when(pdfService).deletePdf(1);

    mockMvc.perform(delete("/api/v1/pdf/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("PDF eliminado correctamente."));
  }

  @Test
  @DisplayName("DELETE /api/v1/pdf/{id} devuelve error 403 si hay una excepción de permisos")
  void testDeletePdfForbidden() throws Exception {
    Mockito.doThrow(new RuntimeException("No tienes permisos"))
        .when(pdfService).deletePdf(99);

    mockMvc.perform(delete("/api/v1/pdf/99"))
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$").value("No tienes permisos"));
  }
}
