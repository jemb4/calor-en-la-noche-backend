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
VALUES (
    'test',
    'https://res.cloudinary.com/djhgovlrz/image/upload/v1761206284/s7rklr3rslf9lmvuvkvf.pdf',
    2022,
    '2025-10-22',
    1
);