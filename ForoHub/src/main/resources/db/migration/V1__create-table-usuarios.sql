CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    clave VARCHAR(255) NOT NULL
);