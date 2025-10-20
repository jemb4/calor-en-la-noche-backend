package dev.jesus.calor_en_la_noche.config.encoder.encrypt;

public class EncryptFacade implements IEncryptFacade {

  private final IEncoder encoder;

  public EncryptFacade(IEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public String encode(String type, String data) {
    String dataEncoded = "";

    if (type.equals("bcrypt"))
      dataEncoded = encoder.encode(data);

    return dataEncoded;
  }
}
