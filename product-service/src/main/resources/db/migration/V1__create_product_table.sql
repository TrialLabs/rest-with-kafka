CREATE TABLE IF NOT EXISTS product(
    id VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    description VARCHAR(255),
    price DECIMAL(20,10) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);