CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    email VARCHAR(100)
);

INSERT INTO usuarios(nombre, email) VALUES ('Juan', 'juan@mail.com');