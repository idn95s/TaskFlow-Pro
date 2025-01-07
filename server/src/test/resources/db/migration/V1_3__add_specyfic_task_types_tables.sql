-- Table: bug_fix_task

-- DROP TABLE IF EXISTS bug_fix_task;

CREATE TABLE IF NOT EXISTS bug_fix_task
(
    deadline_date TIMESTAMP,
    id BIGINT NOT NULL,
    CONSTRAINT bug_fix_task_pkey PRIMARY KEY (id),
    CONSTRAINT fkddwvluvj4sfg97y1atnbamxe7 FOREIGN KEY (id)
        REFERENCES task (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: development_task

-- DROP TABLE IF EXISTS development_task;

CREATE TABLE IF NOT EXISTS development_task
(
    sprint_id BIGINT,
    id BIGINT NOT NULL,
    CONSTRAINT development_task_pkey PRIMARY KEY (id),
    CONSTRAINT fkm7v02y0l2nmkr9mcsgbbh00k5 FOREIGN KEY (id)
        REFERENCES task (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
