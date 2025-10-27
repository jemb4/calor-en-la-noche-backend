package dev.jesus.calor_en_la_noche.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.jesus.calor_en_la_noche.config.encoder.decrypt.DecryptFacade;
import dev.jesus.calor_en_la_noche.config.encoder.decrypt.IDecryptFacade;
import dev.jesus.calor_en_la_noche.config.encoder.encrypt.EncryptFacade;
import dev.jesus.calor_en_la_noche.config.encoder.encrypt.IEncryptFacade;
import dev.jesus.calor_en_la_noche.config.encoder.encryptions_systems.Base64System;
import dev.jesus.calor_en_la_noche.config.encoder.encryptions_systems.BcryptSystem;

@Configuration
public class BeanConfiguration {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Base64System base64System() {
    return new Base64System();
  }

  @Bean
  public IEncryptFacade bcryptSystem() {
    return new EncryptFacade(new BcryptSystem(passwordEncoder()));
  }

  @Bean
  public IDecryptFacade encryptFacade() {
    return new DecryptFacade(base64System());
  }
}
