CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP(6),
    email VARCHAR(255),
    password_hash VARCHAR(255),
    updated_at TIMESTAMP(6),
    username VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO app_user
(created_at, email, password_hash, updated_at, username)
VALUES
(CURRENT_TIMESTAMP, 'test@example.com', 'hashedpassword123', CURRENT_TIMESTAMP, 'testuser');