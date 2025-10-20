package dev.jesus.calor_en_la_noche.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "TU_CLOUD_NAME",
        "api_key", "TU_API_KEY",
        "api_secret", "TU_API_SECRET"));
  }
}
