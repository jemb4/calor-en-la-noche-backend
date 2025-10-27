package dev.jesus.calor_en_la_noche.config.encoder.encryptions_systems;

import org.springframework.security.crypto.password.PasswordEncoder;

import dev.jesus.calor_en_la_noche.config.encoder.encrypt.IEncoder;

public class BcryptSystem implements IEncoder {
  PasswordEncoder encoder;

  public BcryptSystem(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public String encode(String data) {
    String dataEncoded = encoder.encode(data);
    return dataEncoded;
  }
}
