-- =======================
-- Roles
-- =======================
INSERT IGNORE INTO roles (name) VALUES ('ADMIN'), ('USER');

-- =======================
-- Users
-- =======================
INSERT INTO users (email, password_hash)
VALUES 
('admin1@calor.com', 'MTIzNDU2'),
('admin2@calor.com', 'MTIzNDU2');

-- =======================
-- Asign rols
-- =======================
INSERT INTO roles_users (user_id, role_id)
VALUES 
(1, 1),
(2, 1);