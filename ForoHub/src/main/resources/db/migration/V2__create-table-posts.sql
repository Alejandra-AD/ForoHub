CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    contenido TEXT,
    usuario_id INT,
    tema VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP,
    activo BOOLEAN,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

