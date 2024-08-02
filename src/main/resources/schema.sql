CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "users"
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

INSERT INTO users (name, username, password) VALUES
    ('Alice Johnson', 'alice_j', 'password123')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO users (name, username, password) VALUES
    ('Bob Smith', 'bob_s', 'passw0rd!')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO users (name, username, password) VALUES
    ('Charlie Brown', 'charlie_b', 'P@ssw0rd')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO users (name, username, password) VALUES
    ('Diana Prince', 'diana_p', 'WonderW0man')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO users (name, username, password) VALUES
    ('Eve Adams', 'eve_a', 'Ev3ryS3cr3t')
    ON CONFLICT (username) DO NOTHING;