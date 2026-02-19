-- Table File ha
DROP TABLE FILE;

CREATE TABLE FILE (
    id SERIAL PRIMARY KEY ,
    file_group VARCHAR NOT NULL,
    file_name VARCHAR NOT NULL UNIQUE,
    data BYTEA
)