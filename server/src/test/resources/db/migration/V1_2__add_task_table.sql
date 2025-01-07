CREATE TABLE IF NOT EXISTS task
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    description VARCHAR(255),
    priority VARCHAR(255),
    status VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP,
    assigned_to_id BIGINT,
    CONSTRAINT uk20c7byw48jcthxnvt67bbvijq UNIQUE (title),
    CONSTRAINT task_priority_check CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH')),
    CONSTRAINT task_status_check CHECK (status IN ('TODO', 'IN_PROGRESS', 'CODE_REVIEW', 'TESTING', 'DONE'))
);

ALTER TABLE task
    ADD CONSTRAINT fkb58h5uq2nvv7jnvmpltbqv841 FOREIGN KEY (assigned_to_id) 
    REFERENCES app_user(id);
