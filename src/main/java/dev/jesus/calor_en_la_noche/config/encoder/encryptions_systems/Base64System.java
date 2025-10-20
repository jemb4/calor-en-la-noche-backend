package dev.jesus.calor_en_la_noche.config.encoder.encryptions_systems;

import java.util.Base64;

import dev.jesus.calor_en_la_noche.config.encoder.decrypt.IDecoder;

public class Base64System implements IDecoder {

  @Override
  public String decode(String data) {
    byte[] decodedBytes = Base64.getDecoder().decode(data);
    String dataDecoded = new String(decodedBytes);
    return dataDecoded;
  }

}
