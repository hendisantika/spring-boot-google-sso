CREATE TABLE t_permission
(
    id               VARCHAR(255) NOT NULL,
    permission_label VARCHAR(255) NOT NULL,
    permission_value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (permission_value)
);

CREATE TABLE t_role
(
    id          VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    name        VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE t_role_permission
(
    id_role       VARCHAR(255) NOT NULL,
    id_permission VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_role, id_permission),
    FOREIGN KEY (id_permission) REFERENCES t_permission (id),
    FOREIGN KEY (id_role) REFERENCES t_role (id)
);

CREATE TABLE t_user
(
    id       varchar(36),
    username varchar(100) NOT NULL,
    id_role  varchar(36)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_role) REFERENCES t_role (id),
    UNIQUE (username)
);
