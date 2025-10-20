package dev.jesus.calor_en_la_noche.home;

@RestController
public class HomeController {

  @GetMapping()
  public ResponseEntity<HomeDTOResponse> index() {
    return ResponseEntity.ok().body(new HomeDTOResponse(HttpStatus.OK.value(), "Welcome to Veterinary Clinic API"));
  }

  @GetMapping("/public")
  public ResponseEntity<HomeDTOResponse> publicPath() {
    return ResponseEntity.ok().body(new HomeDTOResponse(HttpStatus.OK.value(), "Public path"));
  }

  @GetMapping("/private")
  public ResponseEntity<HomeDTOResponse> privatePath() {
    return ResponseEntity.ok().body(new HomeDTOResponse(HttpStatus.OK.value(), "Private path"));
  }
}
