-- =======================
-- Roles
-- =======================
MERGE INTO roles (name) KEY(name) VALUES ('ADMIN');
MERGE INTO roles (name) KEY(name) VALUES ('USER');
-- =======================
-- Users
-- =======================
MERGE INTO users (email, password_hash) KEY(email)
VALUES ('admin1@calor.com', '$2b$12$rqHC4c10dgjwieGr7zfzJegC5igezfzFZdNOT6ZU1cdkHltQ/8Doe');

MERGE INTO users (email, password_hash) KEY(email)
VALUES ('admin2@calor.com', '$2b$12$rqHC4c10dgjwieGr7zfzJegC5igezfzFZdNOT6ZU1cdkHltQ/8Doe');

-- =======================
-- Asign rols
-- =======================
MERGE INTO roles_users (user_id, role_id) KEY(user_id, role_id) VALUES (1, 1);
MERGE INTO roles_users (user_id, role_id) KEY(user_id, role_id) VALUES (2, 1);