CREATE SEQUENCE user_account_sequence START 1 INCREMENT 1;
CREATE SEQUENCE role_sequence START 1 INCREMENT 1;

CREATE TABLE user_account
(
    id INT8 NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    rolename VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(username)
);

CREATE TABLE role
(
    id INT8 NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);

INSERT INTO role VALUES (1, 'admin', 'admin role');
INSERT INTO role VALUES (2, 'user', 'user role');