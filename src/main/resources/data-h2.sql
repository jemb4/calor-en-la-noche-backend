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