-- =======================
-- Roles
-- =======================
INSERT IGNORE INTO roles (name) VALUES ('ADMIN'), ('USER');

-- =======================
-- Users
-- =======================
INSERT INTO users (email, password_hash)
VALUES 
('admin1@calor.com', '$2b$12$rqHC4c10dgjwieGr7zfzJegC5igezfzFZdNOT6ZU1cdkHltQ/8Doe'),
('admin2@calor.com', '$2b$12$rqHC4c10dgjwieGr7zfzJegC5igezfzFZdNOT6ZU1cdkHltQ/8Doe');

-- =======================
-- Asign rols
-- =======================
INSERT INTO roles_users (user_id, role_id)
VALUES 
(1, 1),
(2, 1);

-- =======================
-- Pdfs
-- =======================
INSERT INTO pdfs (name, url_pdf, age, upload_day, user_id)
VALUES 
(
    'test',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1761916360/dnp0mo9zgjyf0khcwn5w.pdf',
    2022,
    '2025-10-22',
    1
),
(
    'ESTATUTOS DE LA ASOCIACIÓN',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199499/Estatutos_Calor_en_la_Noche_original_compressed_1_pfhdtx.pdf',
    2025,
    '2025-10-22',
    1
),
(
    'MEMORIA ECONÓMICA',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199496/Memoria_Calor_en_la_Noche_2019_e6ymkt.pdf',
    2019,
    '2025-10-22',
    1
),
(
    'MEMORIA ECONÓMICA',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199497/1_Memoria_economica_Balance_2024_1_dnwpd4.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'ORGANO DEL GOBIERNO',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199498/8_Organo_de_gobierno_2025_2027_hynpiw.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'MEMORIA ACTIVIDADES',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199498/2_Memoria_de_actividades_2024_2_kdg22d.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'CERTIFICADO DABO',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199498/7_Certificado_DABO_2024_mtwgje.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'MEMORIA',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199498/Memoria_2021_1_xyktos.pdf',
    2021,
    '2025-10-22',
    1
),
(
    'PERDIDAS Y GANANCIAS',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199498/3_Perdidas_y_Ganancias_2024_2_ypoz7o.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'MEMORIA ASOCIACIÓN',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199497/4_Memoria_de_actividades_2023_1_brg9jd.pdf',
    2024,
    '2025-10-22',
    1
),
(
    'PERDIDAS Y GANANCIAS',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1762199497/6_Perdidas_y_ganancias_2023_1_hp26dr.pdf',
    2023,
    '2025-10-22',
    1
),


;